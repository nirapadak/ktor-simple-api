package org.examle.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.examle.data.getAllCustomer
import org.examle.data.model.Customer

fun Route.userRoute(){

    route("user-get"){
        get {
            call.respond(HttpStatusCode.OK, "what is houter")
        }
    }

    // get all customer -----------------------------
    // how return a list of json --------------------

    route("get-all-customer"){
        get {
            val customerData = getAllCustomer()
            if (customerData == null) {
                HttpStatusCode.OK
                call.respond(mapOf("success" to false, "data" to customerData, "massage" to "No data in this collection"))
            }else{
                HttpStatusCode.OK
                // this is return a json ---------------------------------
                call.respond(mapOf(
                    "success" to true,
                    "massage" to "all data is Call",
                    "data" to customerData,
                    "ListSize" to customerData.size
                ))
            }
        }
    }


}