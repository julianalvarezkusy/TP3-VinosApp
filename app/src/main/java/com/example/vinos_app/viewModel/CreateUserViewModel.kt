package com.example.vinos_app.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vinos_app.entities.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateUserViewModel : ViewModel(){

    val userColection: String = "users"

    val db = Firebase.firestore

    fun addUser(nombre : String, email: String, password: String){
        var user = User(nombre, email, password)
        db.collection(userColection).document(email).set(user)
    }

     fun getUser(email: String) : User?{
        var user : User? = null
        var origin = db.collection(userColection).document(email)
        origin.get()
                .addOnSuccessListener {
                    document ->
                    if(document != null){
                        user = document.data as User
                    }else{

                        Log.d("No such user", "No such user")
                    }

        }
         return user
    }
}