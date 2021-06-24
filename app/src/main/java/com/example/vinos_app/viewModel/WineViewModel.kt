import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinos_app.entities.User
import com.example.vinos_app.entities.Vino
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList as ArrayList


class WineViewModel : ViewModel() {
    var vinos : MutableList<Vino> = mutableListOf()
    var vinosLiveData : MutableLiveData<MutableList<Vino>> = MutableLiveData()

    val db = Firebase.firestore

    fun cargarDatos(){
        vinos.add(Vino("Estrella 1977", 1500.00, 3.8, "Weinert"))
        vinos.add(Vino("La Violeta 2012", 2000.00, 4.8, "Monteviejo"))
        vinos.add(Vino("Nosotros 2010", 1300.00, 3.2, "Susana Balbo"))
        vinos.add(Vino("Chañares", 2005.00, 4.5, "Mendoza"))
        //vinosLD.value = vinos

/*       for (vino in vinos) {
            addWine(vino)
            Log.d(TAG,vino.nombre)
        }
        */

    }

    fun addWine(wine:Vino){

        db.collection("vinos").document(wine.nombre)
            .set(wine)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun addWine (user: User, wine: Vino){
        user.userWineList.add(wine)
        user.email?.let {
            db.collection("users").document(it)
                .set(user)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully written!")

                }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        }
    }

   fun getListWines(){

        db.collection("vinos")
            //.whereEqualTo("nombreVino", nombre)
            .get()
            .addOnSuccessListener {documents ->
                if (documents != null) {
                    Log.d(TAG,"Busqueda de vinos")
                    for (document in documents) {
                        Log.d(TAG, "${document.id} => ${document.data}")
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

}





