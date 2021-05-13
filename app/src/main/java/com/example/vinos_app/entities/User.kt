package com.example.vinos_app.entities

class User (name: String, email: String, password: String){
    var name: String = ""
    var email: String =""
    var password: String =""

    init{
        this.name = name
        this.email = email
        this.password = password
    }
}