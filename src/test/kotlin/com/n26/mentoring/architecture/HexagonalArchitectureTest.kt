package com.n26.mentoring.architecture

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.jupiter.api.Test

class HexagonalArchitectureTest {
    private val classes = ClassFileImporter().importPackages("com.n26.mentoring")

    @Test
    fun `layers respect hexagonal dependency direction`() {
        layeredArchitecture()
            .consideringAllDependencies()
            .optionalLayer("Domain")
            .definedBy("com.n26.mentoring.domain..")
            .optionalLayer("Application")
            .definedBy("com.n26.mentoring.application..")
            .optionalLayer("Infrastructure")
            .definedBy("com.n26.mentoring.infrastructure..")
            .whereLayer("Domain")
            .mayNotAccessAnyLayer()
            .whereLayer("Application")
            .mayOnlyAccessLayers("Domain")
            .whereLayer("Infrastructure")
            .mayOnlyAccessLayers("Application", "Domain")
            .check(classes)
    }

    @Test
    fun `domain must not depend on Spring`() {
        noClasses()
            .that()
            .resideInAPackage("com.n26.mentoring.domain..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("org.springframework..")
            .because("the domain must be framework-agnostic")
            .allowEmptyShould(true)
            .check(classes)
    }

    @Test
    fun `domain must not depend on infrastructure`() {
        noClasses()
            .that()
            .resideInAPackage("com.n26.mentoring.domain..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("com.n26.mentoring.infrastructure..")
            .because("the domain must not know about infrastructure details")
            .allowEmptyShould(true)
            .check(classes)
    }

    @Test
    fun `domain must not depend on application`() {
        noClasses()
            .that()
            .resideInAPackage("com.n26.mentoring.domain..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("com.n26.mentoring.application..")
            .because("the domain is the innermost layer and must have no outward dependencies")
            .allowEmptyShould(true)
            .check(classes)
    }

    @Test
    fun `application must not depend on infrastructure`() {
        noClasses()
            .that()
            .resideInAPackage("com.n26.mentoring.application..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("com.n26.mentoring.infrastructure..")
            .because("use cases must not depend on infrastructure details")
            .allowEmptyShould(true)
            .check(classes)
    }

    @Test
    fun `application must not depend on Spring Web`() {
        noClasses()
            .that()
            .resideInAPackage("com.n26.mentoring.application..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("org.springframework.web..")
            .because("use cases must have no knowledge of HTTP")
            .allowEmptyShould(true)
            .check(classes)
    }

    @Test
    fun `controllers must reside in infrastructure rest`() {
        noClasses()
            .that()
            .areAnnotatedWith("org.springframework.web.bind.annotation.RestController")
            .should()
            .resideOutsideOfPackage("com.n26.mentoring.infrastructure.rest..")
            .because("REST controllers belong in the infrastructure layer")
            .allowEmptyShould(true)
            .check(classes)
    }
}
