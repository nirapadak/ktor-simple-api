package org.example.org.examle

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.examle.plugins.configureSerialization
import org.example.org.examle.plugins.configureRouting


// this is my application start point -------------------
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


@Suppress("unused")
fun Application.module() {
    configureRouting()
    configureSerialization()
}


