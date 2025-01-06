package me.jason5lee.anyup.libericajdk

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class LibericaClient(private val client: HttpClient) {

    suspend fun getArchitecture(): List<String> {
        val l1: List<String> = client.get("$URL_BASE/liberica/architectures")
            .body()
        val l2: List<String> = client.get("$URL_BASE/nik/architectures")
            .body()
        return l1 + l2
    }


    companion object {
        private val URL_BASE: String = "https://api.bell-sw.com/v1"
    }
}
