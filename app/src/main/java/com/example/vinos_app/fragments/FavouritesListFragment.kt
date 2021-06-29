package com.example.vinos_app.fragments

import WineViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinos_app.R
import com.example.vinos_app.adapters.VinoListAdapter
import com.example.vinos_app.viewModel.CreateUserViewModel
import com.google.android.material.snackbar.Snackbar

class FavouritesListFragment : Fragment() {


    lateinit var v: View

    lateinit var recVinos: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var vinoListAdapter: VinoListAdapter

    private lateinit var wineViewModel: WineViewModel
    private lateinit var userViewModel: CreateUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        vinoListAdapter = VinoListAdapter { x,y -> onItemsClick(x,y) }

        wineViewModel = ViewModelProvider(requireActivity()).get(WineViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(CreateUserViewModel::class.java)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_favourites_list, container, false)
        setHasOptionsMenu(true)
        recVinos = v.findViewById(R.id.favouriteRecView)
        return v
    }

    override fun onStart() {
        super.onStart()
        recVinos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recVinos.layoutManager = linearLayoutManager

        //Acá tengo que traer los vinos del user

    }

    private fun onItemsClick(position: Int, item: String): Boolean {
        if (item == "cardView" ){
            cardView(position)
        } else if (item == "fav"){
            itemFav(position)
        }
        return true
    }

    private fun itemFav (position: Int): Boolean {
        Snackbar.make(v, position.toString() + "agregar a Favoritos", Snackbar.LENGTH_SHORT).show()
        return true
    }

    private fun cardView (position:Int ) : Boolean {

        var action: NavDirections = ListFragmentDirections.actionListFragmentToDetailsFragment(position)

        var navController = v.findNavController()
        if (navController.currentDestination?.id == R.id.listFragment) {
            navController.navigate(action)
        }else{
            Snackbar.make(v, "Error" + navController.currentDestination?.label.toString(), Snackbar.LENGTH_SHORT)
                .show()
        }

        return true
    }

}