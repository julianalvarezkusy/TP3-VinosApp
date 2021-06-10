package com.example.vinos_app.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.vinos_app.activities.LoginActivity
import com.example.vinos_app.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class CreateUserViewModel : ViewModel(){

    val userColection: String = "users"

    val db = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth


    fun addUser(nombre : String, email: String, password: String) {
        var miUser = User(nombre, email, password)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { task ->
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                if (user != null) {
                    db.collection(userColection).document(email)
                        .set(miUser)
                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                }
            }
            .addOnFailureListener{
                // If sign in fails, display a message to the user.
                e -> Log.w(TAG, "createUserWithEmail:failure", e )
                //Toast.makeText(, "Authentication failed.",Toast.LENGTH_SHORT).show()}
            }
    }

        suspend fun  getUser(email: String, password: String): Boolean {
            var userValidated: Boolean = false

            try {

                val result = auth.signInWithEmailAndPassword(email, password).await()

                return result.user != null
                Log.d(TAG, "signInWithEmail:success")

                /*.addOnCompleteListener(){ task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            if(user != null)
                                userValidated = true
                            //updateUI(user)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                        }


                }*/
            } catch (e: Exception) {
                Log.e("FirebaseUserSource", "Exception thrown: ${e.message}")

                return false
            }


        }

}