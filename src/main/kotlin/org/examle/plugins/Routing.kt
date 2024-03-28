package org.example.org.examle.plugins


import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.examle.routes.employeeRoutes
import org.examle.routes.userRoute

fun Application.configureRouting() {
    routing {
        employeeRoutes()
        userRoute()

        get("/") {
            call.respondText("my name is Nirapdak pal")
        }
        get("/home"){
            call.respondText("this is my home tab")
        }
    }
}