package org.example.org.examle

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.ratelimit.*
import org.examle.plugins.configureSerialization
import org.example.org.examle.plugins.configureRouting
import kotlin.time.Duration.Companion.seconds


// this is my application start point -------------------
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


@Suppress("unused")
fun Application.module() {


    // add RateLimiter -------------------------------
    // you can request 60 seconds 60 request
    install(RateLimit) {
        global {
            rateLimiter(limit = 60, refillPeriod = 60.seconds)
        }
    }



    configureRouting()
    configureSerialization()

}


