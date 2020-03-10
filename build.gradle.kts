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

dependencies {
    //    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    implementation("io.springfox:springfox-swagger2:2.9.2")
//    implementation("io.springfox:springfox-swagger-ui:2.9.2")
//    runtimeOnly("com.h2database:h2")
//
//    testImplementation("org.springframework.boot:spring-boot-starter-test") {
//        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
//    }
//    testImplementation("io.kotlintest:kotlintest-core:3.4.2")
//    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
//    testImplementation("io.kotlintest:kotlintest-extensions-spring:3.4.2")
//    testImplementation("io.mockk:mockk:1.9.3")
//    testImplementation("com.ninja-squad:springmockk:2.0.0")
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