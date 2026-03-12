plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jetbrains.kotlinx.kover") version "0.8.3"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

group = "com.n26.mentoring"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val integrationTest: SourceSet =
    sourceSets.create("integrationTest") {
        kotlin.srcDir("src/integrationTest/kotlin")
        resources.srcDir("src/integrationTest/resources")
        compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
        runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
    }

val integrationTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.testImplementation.get())
}
val integrationTestRuntimeOnly: Configuration by configurations.getting {
    extendsFrom(configurations.testRuntimeOnly.get())
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("com.tngtech.archunit:archunit-junit5:1.3.0")

    integrationTestImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    integrationTestImplementation("org.testcontainers:junit-jupiter:1.20.1")
    integrationTestImplementation("org.testcontainers:postgresql:1.20.1")
    integrationTestImplementation("org.postgresql:postgresql")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Test>("integrationTest") {
    description = "Runs integration tests against a real Docker environment"
    group = "verification"
    testClassesDirs = integrationTest.output.classesDirs
    classpath = integrationTest.runtimeClasspath
    useJUnitPlatform()
    shouldRunAfter(tasks.test)
    environment("TESTCONTAINERS_RYUK_DISABLED", "true")
}

tasks.check {
    dependsOn(tasks.named("integrationTest"))
}

// Installs git hooks automatically on first build
tasks.register("installGitHooks") {
    description = "Installs git hooks from .githooks/"
    group = "setup"
    doLast {
        providers.exec {
            commandLine("git", "config", "core.hooksPath", ".githooks")
        }
    }
}

tasks.named("build") {
    dependsOn("installGitHooks")
}

ktlint {
    version = "1.5.0"
}

kover {
    reports {
        filters {
            excludes {
                classes("com.n26.mentoring.MentoringApplicationKt")
            }
        }
        verify {
            rule {
                minBound(80)
            }
        }
    }
}
