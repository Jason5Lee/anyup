package me.jason5lee.anyup

object CpuArch {
    val X86 = "x86"
    val X86_64 = "x86_64"
    val ARM32 = "arm32"
    val ARM64 = "arm64"
    val RISCV32 = "riscv32"
    val RISCV64 = "riscv64"
    val PPC32 = "ppc32"
    val PPC64 = "ppc64"
    val SPARC32 = "sparc32"
    val SPARC64 = "sparc64"

    val current: String? = System.getProperty("os.arch")?.let { arch ->
        when (arch.lowercase()) {
            "x86", "i386", "i486", "i586", "i686" -> X86
            "x86_64", "amd64" -> X86_64
            "arm", "armv7l", "aarch32" -> ARM32
            "aarch64", "arm64" -> ARM64
            "riscv32" -> RISCV32
            "riscv64" -> RISCV64
            "ppc", "ppc32" -> PPC32
            "ppc64" -> PPC64
            "sparc", "sparc32" -> SPARC32
            "sparc64" -> SPARC64
            else -> null
        }
    }

    fun help(allArch: List<String>, default: String?): String {
        val sb = StringBuilder("The CPU Architecture. Available values:\u0085")
        var notFirst = false
        for (arch in allArch) {
            if (notFirst) {
                sb.append(", ")
            } else {
                notFirst = true
            }

            sb.append("**")
            sb.append(arch)
            sb.append("**")
            if (arch == default) {
                sb.append(" [DEFAULT]")
            }
        }

        return sb.toString()
    }
}