repositories { mavenCentral() }

plugins {
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.25"
    id("com.avast.gradle.docker-compose") version "0.17.5"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// Optional: DB-Container bequem per Gradle starten/stoppen
dockerCompose {
    useComposeFiles = listOf("docker-compose.yml")
    startedServices = listOf("db")
    // Falls du dein Compose mit Profil "db" ergänzt hast:
    // startedServices = emptyList()
    // upAdditionalArgs = listOf("--profile", "db")
    stopContainers = true
    removeContainers = true
    // Wenn du Logs in Gradle sehen willst:
    // captureContainersOutput = true
}

tasks.register("dbUp") {
    dependsOn("composeUp")
}
tasks.register("dbDown") {
    dependsOn("composeDown")
}
