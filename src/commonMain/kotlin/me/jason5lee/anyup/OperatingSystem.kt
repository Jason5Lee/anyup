package me.jason5lee.anyup

class OperatingSystem(val identifier: String, val name: String) {
    companion object {
        val LINUX: OperatingSystem = OperatingSystem("linux", "Linux")
        val LINUX_MUSL: OperatingSystem = OperatingSystem("linux-musl", "Linux (musl)")
        val WINDOWS: OperatingSystem = OperatingSystem("windows", "Windows")
        val MACOS: OperatingSystem = OperatingSystem("macos", "Mac OS X")
        val SOLARIS: OperatingSystem = OperatingSystem("solaris", "Solaris")

        val current: OperatingSystem? = System.getProperty("os.name")?.let { osName ->
            when {
                osName.startsWith("Windows") -> WINDOWS
                osName.startsWith("Mac OS X") -> MACOS
                osName.startsWith("Linux") -> LINUX
                osName.startsWith("Solaris") -> SOLARIS
                else -> null
            }
        }

        fun help(allOs: List<OperatingSystem>, default: OperatingSystem? = current): String {
            val sb = StringBuilder("The Operating System. Available values:\u0085")
            for (os in allOs) {
                sb.append("  **")
                sb.append(os.identifier)
                sb.append("**: ")
                sb.append(os.name)
                if (default != null && os.identifier == default.identifier) {
                    sb.append(" [DEFAULT]")
                }
                sb.append('\u0085')
            }

            return sb.toString()
        }
    }
}