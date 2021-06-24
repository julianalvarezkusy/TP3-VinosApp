package com.example.vinos_app.fragments

import WineViewModel
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
<<<<<<< HEAD
<<<<<<< HEAD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
=======
>>>>>>> 4dcf3670023c8d16888333765fb6a82ee4f40183
=======
>>>>>>> 4dcf3670023c8d16888333765fb6a82ee4f40183
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.vinos_app.R
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.vinos_app.viewModel.CreateUserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import java.lang.Exception
=======
import com.google.android.material.snackbar.Snackbar
>>>>>>> 4dcf3670023c8d16888333765fb6a82ee4f40183
=======
import com.google.android.material.snackbar.Snackbar
>>>>>>> 4dcf3670023c8d16888333765fb6a82ee4f40183

class DetailsFragment : Fragment() {

    lateinit var v: View


    lateinit var wineName: TextView
    lateinit var wineCellar: TextView
    lateinit var wineRating: TextView
    lateinit var wineFavourite: Button

    private lateinit var viewModel: WineViewModel
    private lateinit var userViewModel : CreateUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_details, container, false)
        wineName = v.findViewById(R.id.wineNameDetail)
        wineCellar = v.findViewById(R.id.wineCellarDetail)
        wineRating = v.findViewById(R.id.wineRatingDetail)
        wineFavourite = v.findViewById(R.id.favoriteButton)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(WineViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(CreateUserViewModel::class.java)
        // TODO: Use the ViewModel

    }

    override fun onStart() {
        super.onStart()

        //var datoNavigation = FragmentDetalleListArgs.fromBundle(requireArguments())
        var args = DetailsFragmentArgs.fromBundle(requireArguments())

        //var wineObj = viewModel.buscarVino(datoNavigation.wineName)
        var wineObj = viewModel.vinosLiveData.value!![args.wineName]

        if(wineObj != null){
            Log.d("Vino encontrado",wineObj.toString())
            Log.d("ID VINO", args.wineName.toString())
        }

        wineName.text = "Nombre: " + wineObj.nombre
        wineCellar.text = "Cellar: " + wineObj.bodega
        wineRating.text = "Rating: " + wineObj.rating
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_wine_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
<<<<<<< HEAD

<<<<<<< HEAD
        wineFavourite.setOnClickListener {

            val parentJob = Job()
            val scope = CoroutineScope(Dispatchers.Default + parentJob)

            scope.launch {
                try{
                    val userConected = userViewModel.getUserConected()
                    if(userConected!= null){
                        val getUserbyEmail = async { userConected.email?.let { it1 -> userViewModel.getUserByEmail(it1) } }
                        val user = getUserbyEmail.await()
                        if(user != null){
                            viewModel.addWine(user , wineObj)
                            Snackbar.make(v, "Vino agregado correctamente", Snackbar.LENGTH_SHORT)
                                    .show()
                        }else{
                            Log.d("Error", "No se encontró el usuario")
                        }
                    }


                }catch (e : Exception){
                    Log.d("","ERROR: "+ e.message)
                }



=======
>>>>>>> 4dcf3670023c8d16888333765fb6a82ee4f40183

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

<<<<<<< HEAD
            }




        }

=======
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
>>>>>>> 4dcf3670023c8d16888333765fb6a82ee4f40183

        val id = when(item.itemId) {

=======
        val id = when(item.itemId) {

>>>>>>> 4dcf3670023c8d16888333765fb6a82ee4f40183
            R.id.app_bar_switch -> Snackbar.make(v, "bar_switch", Snackbar.LENGTH_SHORT).show()

            else -> ""
        }
        return super.onOptionsItemSelected(item)
    }



}
