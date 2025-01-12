package me.jason5lee.anyup

//class Help(
//    val arch: String?,
//    val os: String?,
//    val flavor: String?
//)

class Platform(
    val arch: String?,
    val os: String?,
    val flavor: String?,
)

class VersionFilter(
    val useNonLts: Boolean,
    val useEa: Boolean,
    val useEol: Boolean,
)

interface Toolchain {
    val name: String

    val allCpuArch: List<String>?
    val allOs: List<OperatingSystem>?
    val allFlavor: List<String>?
    val flavorHelp: String?

    suspend fun getMajorVersions(platform: Platform, filter: VersionFilter): List<String>
    suspend fun getAllVersions(platform: Platform, majorVersion: String?, filter: VersionFilter): List<String>
}
