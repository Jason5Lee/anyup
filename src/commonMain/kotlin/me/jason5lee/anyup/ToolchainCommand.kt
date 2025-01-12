package me.jason5lee.anyup

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.OptionWithValues
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.random.Random

class ToolchainCommand(toolchain: Toolchain) : CliktCommand() {
    init {
        subcommands(ListVersionCommand(toolchain))
    }
    private class ListVersionCommand(toolchain: Toolchain) : CliktCommand() {
        private val arch: String? by toolchain.allCpuArch.orNullDelegate { allCpuArch ->
            option().choice(*allCpuArch.toTypedArray())
                .help(CpuArch.help(allCpuArch, CpuArch.current))
        }
        private val os: String? by toolchain.allOs.orNullDelegate { allOs ->
            option().choice(*allOs.mapTo(mutableListOf()) { it.identifier }.toTypedArray())
                .help(OperatingSystem.help(allOs, OperatingSystem.current))
        }
        private val flavor: String? by toolchain.allFlavor.orNullDelegate { allFlavor ->
            option().choice(*allFlavor.toTypedArray())
                .run {
                    toolchain.flavorHelp?.let { flavorHelp ->
                        help(flavorHelp)
                    } ?: this
                }
        }

        override fun run() {
            println("Arch: ${arch ?: "<null>"}")
        }
    }

    override fun run() {}
}