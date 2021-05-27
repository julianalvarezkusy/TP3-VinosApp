package com.example.vinos_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.vinos_app.entities.Vino
import com.example.vinos_app.fragments.ListFragmentDirections
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class WineViewModel : ViewModel() {
    var vinos : MutableList<Vino> = ArrayList()

    fun cargarDatos(){
        vinos.add(Vino("Estrella 1977", 1500.00, 4.8, "Weinert"))
        vinos.add(Vino("La Violeta 2012", 2000.00, 4.8, "Monteviejo"))
        vinos.add(Vino("Nosotros 2010", 1300.00, 4.7, "Susana Balbo"))
        vinos.add(Vino("Chañares", 2005.00, 4.7, "Mendoza"))

        val db = Firebase.firestore

        var vinoTest = Vino("Estrella 1977", 1500.00, 4.8, "Weinert")

        db.collection("vinos").document(vinoTest.nombreVino).set(vinoTest)

        db.collection("vinos").add(vinoTest)
    }


    fun buscarVino(name:String): Vino? {
        return this.vinos.find { v -> v.nombreVino == name}
    }

}





