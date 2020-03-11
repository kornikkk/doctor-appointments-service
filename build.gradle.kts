plugins {

    kotlin("jvm") version "1.3.61"
}

allprojects {
    group = "pl.kornikkk"
    version = "1.0"

    repositories {
        mavenCentral()
    }

}

tasks {
    test {
        useJUnitPlatform()
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}