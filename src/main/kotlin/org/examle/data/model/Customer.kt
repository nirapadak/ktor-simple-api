package org.examle.data.model

data class Customer(
    val name: String,
    val address: String,
    val zipCode: String,
    val phone: Int,
    val email: String,
    val password: String,
    val otp: String
)