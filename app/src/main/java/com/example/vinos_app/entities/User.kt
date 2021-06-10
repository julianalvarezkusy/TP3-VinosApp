package com.example.vinos_app.entities

class User(
        val name: String? = "",
        val email: String? = "",
        val password: String? = "") {

    //var name: String? = ""
    //var email: String? =""
    //var password: String? =""



    /* init{
        this.name = name
        this.email = email
        this.password = password
    }*/

    override fun toString()= "$name $email $password"

}