package pl.przemek.storage_buddy;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class ArchitectureTests {

    private final JavaClasses importedClasses = new ClassFileImporter().importPackages("pl.przemek");

    @Test
    void classesShouldBePackagePrivateExceptFacades() {
        classes()
                .that()
                .areNotInterfaces()
                .and()
                .haveNameNotMatching(".*Facade")
                .and()
                .resideOutsideOfPackage("..common..")
                .and()
                .resideOutsideOfPackage("..dto..")
                .and()
                .resideOutsideOfPackage("..exception..")
                .should()
                .bePackagePrivate()
                .check(importedClasses);
    }

    @Test
    void dtoClassesShouldBePublic() {
        classes().that().resideInAPackage("..dto..").should().bePublic().check(importedClasses);
    }

    @Test
    void exceptionClassesShouldBePublic() {
        classes().that().resideInAPackage("..exception..").should().bePublic().check(importedClasses);
    }
}
