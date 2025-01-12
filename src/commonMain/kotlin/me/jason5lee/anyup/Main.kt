package me.jason5lee.anyup

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.installMordant
import com.github.ajalt.clikt.core.main
import io.ktor.client.engine.java.*
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.core.terminal
import com.github.ajalt.clikt.output.MordantMarkdownHelpFormatter
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.terminal.Terminal
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import java.net.ProxySelector
import java.net.URI

class Cli() : CliktCommand() {
    init {
//        installMordantMarkdown()
        installMordant(force = true)
//        WindowsAnsi.setup()
        configureContext {
            terminal = Terminal(AnsiLevel.TRUECOLOR)
            helpFormatter = { MordantMarkdownHelpFormatter(it) }
        }

        subcommands(ToolchainCommand(LibericaJdk()))
    }

    override fun run() {}
}

fun main(args: Array<String>) {
    // These must NOT be run in `suspend fun`
    System.setProperty("java.net.useSystemProxies", "true")
    val systemProxy = ProxySelector.getDefault().select(URI("http://foo/bar"))

    val client = HttpClient(Java) {
        install(ContentNegotiation) {
            json()
        }

        engine {
            systemProxy.firstOrNull()?.let {
                println(it)
                proxy = it
            }
        }
    }

    Cli().main(arrayOf("--help"))
}