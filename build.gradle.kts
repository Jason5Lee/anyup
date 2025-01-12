plugins {
    application
    kotlin("multiplatform") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
}

group = "me.jason5lee"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        withJava()
    }//

    // Currently, Kotlin Native is disabled, GraalVM Native Image is used.
    //    val hostOs = System.getProperty("os.name")
    //    val nativeTarget = when {
    //        hostOs == "Mac OS X" -> macosX64("native")
    //        hostOs == "Linux" -> linuxX64("native")
    //        hostOs.startsWith("Windows") -> mingwX64("native")
    //        else -> throw GradleException("Unsupported operating system: $hostOs")
    //    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.ajalt.clikt:clikt:5.0.2")
                implementation("com.github.ajalt.clikt:clikt-markdown:5.0.2")
                implementation("io.ktor:ktor-client-core:3.0.3")
                implementation("io.ktor:ktor-client-content-negotiation:3.0.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-java:3.0.3")
            }
        }
//        val nativeMain by getting
//        val nativeTest by getting
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("me.jason5lee.anyup.MainKt")
}
