package org.examle.data

import org.bson.types.ObjectId
import org.examle.data.model.Customer
import org.examle.data.model.Employee
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

// how to create a database and collection

// CompanyDatabase create ------------------------------
private val client = KMongo.createClient().coroutine
private val database = client.getDatabase("CompanyDatabase")

// Employee collection create --------------------------
private val employees = database.getCollection<Employee>()


// get Employee ---------------------------
suspend fun getEmployeeForId(id: String): Employee? {
    return employees.findOneById(id)
}



// how to create a employee table -------------------------------

suspend fun createEmployeeOrUpdateEmployeeForId(employee: Employee): Boolean {
    // find this employee if exists or does not exists ----------
    val employeeExists = employees.findOneById(employee.id) != null
    return if (employeeExists) {
        employees.updateOneById(employee.id, employee).wasAcknowledged()
    } else {
        employee.id = ObjectId().toString()
        // wasAcknowledged function return true if successful delete
        employees.insertOne(employee).wasAcknowledged()
    }
}

// delete a table ----------------------------------------
suspend fun deleteEmployeeForId(employeeId: String): Boolean {
    // eq meaning " == " this is mongodb keyword --------------
    val employee = employees.findOne(Employee::id eq employeeId)
    employee?.let { employee ->
        // wasAcknowledged function return true if successful delete
        return employees.deleteOneById(employee.id).wasAcknowledged()
    } ?: return false

}


// how to update this employee table

// postman "id": "employee id" then update this employee table this information -


// =================================================================

// create a customer data table

private val customers = database.getCollection<Customer>()

// create customer -----------------------------------------
suspend fun createCustomerForId(customer: Customer): Boolean{
    val customerExists = customers.findOne(Customer::email eq customer.email)!=null
    if (customerExists){
        return false
    }else{
        return customers.insertOne(customer).wasAcknowledged()
    }

}

// get all customer --------------------------------------

suspend fun getAllCustomer(): List<Customer>{
    return customers.find()
        .toList()
}
