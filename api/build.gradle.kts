plugins {
    kotlin("jvm")
    id("maven-publish")
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

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                from(components["java"])
                groupId = "de.joelneumann.kmp-pf4j-proof-of-concept"
                artifactId = "api"
                version = "1.0.0"
            }
        }
    }
}