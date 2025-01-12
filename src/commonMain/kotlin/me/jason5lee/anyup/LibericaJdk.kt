package me.jason5lee.anyup

import io.ktor.client.HttpClient

class LibericaJdk(private val httpClient: HttpClient) : Toolchain {
    override val name: String
        get() = "liberica-jdk"

    override val allCpuArch: List<String>? = CpuArch.run {
        listOf(X86_64, ARM64, RISCV64, X86, ARM32, RISCV32, PPC64, SPARC64, PPC32, SPARC32)
    }
    override val allOs: List<OperatingSystem>? = OperatingSystem.run {
        listOf(LINUX, WINDOWS, MACOS, LINUX_MUSL, SOLARIS)
    }
    override val allFlavor: List<String>? =
        listOf("jdk", "jdk-full", "jdk-lite", "jre", "jre-full", "nik-core", "nik-standard", "nik-full")
    override val flavorHelp: String? = """This parameter allows you to specify which type of Liberica JDK or NIK to use, based on your application's needs. The available options include both Java SE Development Kit (JDK) and Java SE Runtime Environment (JRE) distributions, as well as the Liberica Native Image Kit (NIK), which enables Java bytecode to be compiled into native executables.  

#### **JDK and JRE Options**  

These distributions are tailored for running, compiling, and debugging Java applications or for lightweight runtime environments:  
- **`jdk` (Standard version):** A full Java SE Development Kit optimized for server and desktop deployments without additional components.  
- **`jdk-full` (Full version):** Includes LibericaFX (based on OpenJFX) and Minimal VM, providing a more feature-complete development environment.  
- **`jdk-lite` (Lite version):** Optimized for size, making it ideal for cloud deployments.  
- **`jre` (Standard version):** A lightweight Java Runtime Environment for running simple Java applications.  
- **`jre-full` (Full version):** Includes LibericaFX and Minimal VM for a richer runtime experience.  

#### **NIK (Native Image Kit) Options**  

These distributions are designed for building native executables from Java bytecode for improved performance and startup time:  
- **`nik-core` (Core version):** A minimal distribution with Liberica VM and native image (based on GraalVM), suitable for Java development.  
- **`nik-standard` (Standard version):** Adds support for plugins to enable the use of non-Java programming languages.  
- **`nik-full` (Full version):** A comprehensive build that includes LibericaFX for GUI-based applications."""

    override suspend fun getMajorVersions(
        platform: Platform,
        filter: VersionFilter
    ): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllVersions(
        platform: Platform,
        majorVersion: String?,
        filter: VersionFilter
    ): List<String> {
        TODO("Not yet implemented")
    }

    companion object {
        val DEFAULT_BASE_URL: String = "https://api.bell-sw.com/v1"
    }
}