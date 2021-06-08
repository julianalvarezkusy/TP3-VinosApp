package com.example.vinos_app.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.vinos_app.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateUserViewModel : ViewModel(){

    val userColection: String = "users"

    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth


    fun addUser(nombre : String, email: String, password: String) {
        var user = User(nombre, email, password)
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { task ->
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                if (user != null) {
                    db.collection(userColection).document(email).set(user)
                }
            }
            .addOnFailureListener{
                // If sign in fails, display a message to the user.
                e -> Log.w(TAG, "createUserWithEmail:failure", e )
                //Toast.makeText(, "Authentication failed.",Toast.LENGTH_SHORT).show()}
            }
    }

        fun getUser(email: String): User? {
            var user: User? = null
            var origin = db.collection(userColection).document(email)
            origin.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        user = document.data as User
                    } else {

                        Log.d("No such user", "No such user")
                    }

                }
            return user
        }

}