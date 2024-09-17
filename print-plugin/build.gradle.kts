plugins {
    kotlin("jvm")

    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
}

group = "de.joelneumann"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val pf4jVersion: String by project

dependencies {
    //PF4J
    implementation(project(":api"))
    implementation("org.pf4j:pf4j:${pf4jVersion}")
    ksp("care.better.pf4j:pf4j-kotlin-symbol-processing:2.0.20-1.0.1")


    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

sourceSets.main {
    java.srcDir("build/generated/ksp/main/kotlin")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}