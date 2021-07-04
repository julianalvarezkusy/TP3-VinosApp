import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinos_app.entities.User
import com.example.vinos_app.entities.Vino
import com.example.vinos_app.viewModel.CreateUserViewModel
import com.google.common.collect.Collections2.filter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.io.File
import kotlin.collections.ArrayList as ArrayList


class WineViewModel : ViewModel() {
    var vinos : MutableList<Vino> = mutableListOf()
    var vinosLiveData : MutableLiveData<MutableList<Vino>> = MutableLiveData()


    val db = Firebase.firestore
    var storageRef = Firebase.storage.reference

    fun cargarDatos(){

/*

        vinos.add(Vino("Estrella 1977", 1500.00, 3.8, "Weinert"))
        vinos.add(Vino("La Violeta 2012", 2000.00, 4.8, "Monteviejo"))
        vinos.add(Vino("Nosotros 2010", 1300.00, 3.2, "Susana Balbo"))
        vinos.add(Vino("Chañares", 2005.00, 4.5, "Mendoza"))

        vinosLD.value = vinos
        for (vino in vinos) {
            addWine(vino)
            Log.d(TAG,vino.nombre)
        }

*/

    }

    fun addWine(position: Int){



    }

    fun addWine (user: User, wine: Vino): Boolean{
        var wineAdded = false
        if(!searchDuplicate(user, wine)){
            Log.d("Lista", user.userWineList.toString())
            user.userWineList.add(wine)
            wineAdded= true
            }

        user.email?.let {
            db.collection("users").document(it)
                    .set(user)
                    .addOnSuccessListener {
                        Log.d(TAG, "DocumentSnapshot successfully written!")

                    }
                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        }
        return wineAdded
    }


    private fun searchDuplicate(user: User, wine: Vino): Boolean {
        var userFound : Boolean = false
        var i = 0
        while(i < user.userWineList.size && userFound == false) {

            if (user.userWineList[i].nombre == wine.nombre) {

                userFound = user.userWineList.remove(user.userWineList[i])
                Log.d("Lista", user.userWineList.toString())

            }
            i++
        }
        return userFound
    }

    fun getListWines(){

        db.collection("vinos")
            //.whereEqualTo("nombreVino", nombre)
            .get()
            .addOnSuccessListener {documents ->
                if (documents != null) {

                    for (document in documents) {

                        vinos.add(document.toObject<Vino>())
                        vinosLiveData.value = vinos
                    }
                }

            }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting documents: ", e) }
    }

    fun buscarVino(name:String): Vino? {
        return this.vinos.find { v -> v.nombre == name}
    }



    fun filtroVinos(busquedaString: String) {
        this.vinos.filter { vino:Vino -> "${vino.nombre} => ${vino.bodega}".contains(busquedaString,true) }
        vinosLiveData.value = vinos
    }

    fun uploadWine(vino: Vino): Boolean{
        var vinoGuardado = false
        db.collection("vinos").document().set(vino)
            .addOnSuccessListener {
                vinoGuardado = true
                Log.d("vinos", "Vino Guardado")
            }
            .addOnFailureListener{ Log.d("vinos", "Error al cargar vino")}
        return vinoGuardado
    }

    fun uploadWineImage(image: File):String{
        var imageUrl =""

        var file = Uri.fromFile(image)
        val imageRef = storageRef.child("images/${file.lastPathSegment}")
        imageRef.putFile(file)
            .addOnSuccessListener {
                Log.d(TAG, "Image successfully upload")
                imageUrl = imageRef.downloadUrl.toString()
            }
            .addOnFailureListener {
                    e:Exception -> Log.w(TAG, "Error upload Image", e)
            }
        return imageUrl
    }

}





