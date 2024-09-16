import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "de.joelneumann"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}


val pf4jVersion: String by project

dependencies {
    implementation(compose.desktop.currentOs)

    //PF4J
    implementation(project(":api"))
    implementation("org.pf4j:pf4j:${pf4jVersion}")
    implementation("ch.qos.logback:logback-classic:1.4.12")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "kmp-pf4j-proof-of-concept"
            packageVersion = "1.0.0"
        }
    }
}
