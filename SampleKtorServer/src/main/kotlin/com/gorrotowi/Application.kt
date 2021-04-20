package com.gorrotowi

import com.gorrotowi.plugins.configureRouting
import com.gorrotowi.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.jetty.*

fun main() {
    embeddedServer(Jetty, port = 8080) {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
