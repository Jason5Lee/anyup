package me.jason5lee.anyup.libericajdk

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class LibericaClient(
    private val client: HttpClient,
    private val baseUrl: Url,
) {
    private fun fullUrl(vararg components: String): Url =
        URLBuilder(baseUrl).appendPathSegments(*components).build()

    suspend fun getHosts(): List<String> {
        val result = mutableListOf<String>()
        run {
            val allArch: List<String> = client.get(fullUrl("liberica", "architectures")).body()
            val allOs: List<String> = client.get(fullUrl("liberica", "operating-systems")).body()
            for (arch in allArch) {
                for (os in allOs) {
                    result.add("$arch-$os")
                }
            }
        }

        run {
            val allArch: List<String> = client.get(fullUrl("nik", "architectures")).body()
            val allOs: List<String> = client.get(fullUrl("nik", "operating-systems")).body()
            for (arch in allArch) {
                for (os in allOs) {
                    result.add("$arch-$os-nik")
                }
            }
        }

        return result
    }

    companion object {
        val DEFAULT_BASE_URL: String = "https://api.bell-sw.com/v1"
    }
}
