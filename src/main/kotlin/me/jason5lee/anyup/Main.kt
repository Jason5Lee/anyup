package me.jason5lee.anyup

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import me.jason5lee.anyup.libericajdk.LibericaClient
import java.net.ProxySelector
import java.net.URI

fun main() {
    System.setProperty("java.net.useSystemProxies", "true")
    val systemProxy = ProxySelector.getDefault().select(URI("http://foo/bar"))

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }

        engine {
            systemProxy.firstOrNull()?.let {
                println(it)
                proxy = it }
        }
    }.let { LibericaClient(it, Url(LibericaClient.DEFAULT_BASE_URL)) }

    // Note: we cannot use suspend main because getting system proxy not working for some reason. TODO: proofread
    runBlocking {
        println(client.getHosts())
    }
}