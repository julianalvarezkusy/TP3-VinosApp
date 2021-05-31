package com.example.vinos_app.viewModel

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.JsonReader
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.vinos_app.entities.Vino
import com.example.vinos_app.fragments.ListFragmentDirections
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.log


class WineViewModel : ViewModel() {
    var vinos : MutableList<Vino> = ArrayList()
    val db = Firebase.firestore

    fun cargarDatos(){

        vinos.add(Vino("Estrella 1977", 1500.00, 3.8, "Weinert"))
        vinos.add(Vino("La Violeta 2012", 2000.00, 4.8, "Monteviejo"))
        vinos.add(Vino("Nosotros 2010", 1300.00, 3.2, "Susana Balbo"))
        vinos.add(Vino("Chañares", 2005.00, 4.5, "Mendoza"))

        for (vino in vinos) {
            addWine(vino)
            Log.d(TAG,vino.nombreVino)
        }

    }

    fun addWine(wine:Vino){
        db.collection("vinos").document(wine.nombreVino)
            .set(wine)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun getListWines(){

        db.collection("vinos")
            //.whereEqualTo("nombreVino", nombre)
            .get()
            .addOnSuccessListener { documents ->
                Log.d(TAG,"Busqueda de vinos")
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    fun buscarVino(name:String): Vino? {
        return this.vinos.find { v -> v.nombreVino == name}
    }

}





