package com.example.vinos_app.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vinos_app.entities.User
import com.example.vinos_app.entities.Vino
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class CreateUserViewModel : ViewModel(){

    val userColection: String = "users"

    val db = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth


    fun addUser(nombre : String, email: String, password: String): Boolean {
        var userCreated: Boolean = false
        var userWineList: MutableList<Vino> = mutableListOf()

        var miUser = User(nombre, email, password, userWineList)

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
                userCreated = true
            }
            .addOnFailureListener{
                // If sign in fails, display a message to the user.
                e -> Log.w(TAG, "createUserWithEmail:failure", e )
                userCreated = false
                //Toast.makeText(, "Authentication failed.",Toast.LENGTH_SHORT).show()}
            }
        return userCreated
    }

    fun addUser(user: User){
        db.collection(userColection).document(user.email.toString())
                .set(user)
                .addOnSuccessListener { Log.d(TAG,"Usuario Actualizado correctamente") }
                .addOnFailureListener{e -> Log.w(TAG, "Error al guardar user")}
    }

        suspend fun  getUser(email: String, password: String): Boolean {


            try {

                val result = auth.signInWithEmailAndPassword(email, password).await()

                return result.user != null
                Log.d(TAG, "signInWithEmail:success")


            } catch (e: Exception) {
                Log.e("FirebaseUserSource", "Exception thrown: ${e.message}")

                return false
            }


        }

    fun getUserConected(): FirebaseUser?{
         return auth.currentUser

    }

    suspend fun getAppUserConected() : User?{
        try{

            val user = getUserConected()
            if(user != null){
                val userRef = db.collection(userColection).whereEqualTo("email", user.email.toString())
                    .get().await()
                val userList = userRef.documents
                if (userList.size == 1){

                    return userList[0].toObject(User::class.java)
                }else{
                    return null
                }
                Log.d("FirebaseUserSource", "User found")

            }else{
                return null
            }

        }catch (e: Exception){

            Log.e("User not found", "Exception thrown: ${e.message}")
            return null
        }
    }

    suspend fun getUserByEmail(email: String) : User? {

        try{
            val userRef = db.collection(userColection).whereEqualTo("email", email)
                    .get().await()


            val userList = userRef.documents

            if (userList.size == 1){

                return userList[0].toObject(User::class.java)
            }else{
                return null
            }
            Log.d("FirebaseUserSource", "User found")







        }catch (e: Exception){

            Log.e("User not found", "Exception thrown: ${e.message}")
            return null
        }
    }

    fun updatePassword(newPassword : String){
            Log.d("", "NewPass: " + newPassword)
            var firebaseUser = getUserConected()

        if(firebaseUser != null){
            firebaseUser.updatePassword(newPassword)
                .addOnSuccessListener { Log.d("","Password Updated") }
                .addOnFailureListener{Log.d("","Error")}
        }else{
            Log.d("", "User not found")
        }





    }



}