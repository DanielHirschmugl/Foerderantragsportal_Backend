repositories {
    mavenCentral()
}

plugins {
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"   // <-- wichtig für Spring-Integration
    kotlin("plugin.jpa") version "1.9.25"      // <-- wichtig für ORM (keine No-Arg Fehler)
    kotlin("kapt") version "1.9.25"            // <-- HINZUGEFÜGT: Für Annotation Processors wie Lombok
    id("com.avast.gradle.docker-compose") version "0.17.5"
}

springBoot {
    mainClass.set("org.example.backend.BackendApplicationKt")
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
    // --- Spring + ORM ---
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // --- Kotlin Integration ---
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // JSON <-> Kotlin data classes
    implementation("org.jetbrains.kotlin:kotlin-reflect")                // für Reflection bei JPA
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // --- Lombok (HINZUGEFÜGT) ---
    compileOnly("org.projectlombok:lombok")
    kapt("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    kaptTest("org.projectlombok:lombok") // <-- KORRIGIERT (war testKapt)
    // --- ENDE HINZUGEFÜGT ---

    // --- Datenbanktreiber ---
    runtimeOnly("org.postgresql:postgresql")

    // --- Tests ---
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// Optional: DB-Container bequem per Gradle starten/stoppen
dockerCompose {
    useComposeFiles = listOf("docker-compose.yml")
    startedServices = listOf("db")
    stopContainers = true
    removeContainers = true
    // captureContainersOutput = true // Optional: Logausgabe in Gradle
}

tasks.register("dbUp") {
    dependsOn("composeUp")
}
tasks.register("dbDown") {
    dependsOn("composeDown")
}