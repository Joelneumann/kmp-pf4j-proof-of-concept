plugins {
    kotlin("jvm")
}

group = "de.joelneumann"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val pf4jVersion: String by project

dependencies {
    implementation("org.pf4j:pf4j:${pf4jVersion}")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}