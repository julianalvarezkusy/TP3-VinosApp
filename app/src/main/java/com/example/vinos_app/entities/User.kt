package com.example.vinos_app.entities

class User(
        val name: String? = "",
        val email: String? = "",
        val password: String? = "",
        val userWineList: MutableList<Vino>) {

        constructor() : this("", "", "", ArrayList())


    override fun toString()= "$name $email $password ${userWineList.size}"

}