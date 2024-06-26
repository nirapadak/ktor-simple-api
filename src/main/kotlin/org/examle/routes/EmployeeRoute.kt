package org.examle.routes

import ch.qos.logback.classic.pattern.Util
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.examle.data.*
import org.examle.data.model.Customer
import org.examle.data.model.Employee
import org.examle.data.requests.DeleteEmployeeRequest
import org.examle.data.requests.EmployeeRequest
import org.litote.kmongo.MongoOperator

fun Route.employeeRoutes() {

    route("/about"){
        get {
            call.respond("this is about page")
        }
    }


    route("/get-employee") {
        get {
            val employeeId = call.receive<EmployeeRequest>().id
            val employee = getEmployeeForId(employeeId)
            employee?.let {
                call.respond(
                    HttpStatusCode.OK,
                    SimpleResponse(true, "Employee successfully retrieved", it)
                )
            } ?: call.respond(
                HttpStatusCode.OK,
                SimpleResponse(true, "There is no employee with this id", Unit)

            )

        }
    }

    route("/create-update-employee") {
        post {
            val request = try {
                call.receive<Employee>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            if (createEmployeeOrUpdateEmployeeForId(request)) {
                call.respond(
                    HttpStatusCode.OK,
                    SimpleResponse(true, "Employee successfully created/ updated", it)

                )
            } else {
                call.respond(HttpStatusCode.Conflict)
            }
        }
    }


    route("/delete-employee") {
        post {
            val request = try {
                call.receive<DeleteEmployeeRequest>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            if (deleteEmployeeForId(request.id)) {
                call.respond(
                    HttpStatusCode.OK,
                    SimpleResponse(true, "Employee successfully deleted", Unit)
                )
            } else {
                call.respond(
                    HttpStatusCode.OK,
                    SimpleResponse(false, "Employee not found", Unit)
                )
            }
        }
    }

    route("create-customer"){
        post {
            val request = try{
                call.receive<Customer>()
        }catch (e: ContentTransformationException){
            call.respond(HttpStatusCode.BadRequest)
                return@post
        }

            if (createCustomerForId(request)){
                call.respond(
                    HttpStatusCode.OK,
                    SimpleResponse(true, "Customer Created Successful", it)

                )
            }else{
                call.respond(
                    HttpStatusCode.OK,
                    SimpleResponse(false, "Customer save not successful/ Customer already exists", Unit)

                )
            }



        }
    }

}