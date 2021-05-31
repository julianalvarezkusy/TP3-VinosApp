package com.example.vinos_app.fragments

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
import com.example.vinos_app.viewModel.WineViewModel
import com.google.android.material.snackbar.Snackbar


class ListFragment : Fragment() {

    lateinit var v:View

    lateinit var recVinos: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var vinoListAdapter : VinoListAdapter

    private lateinit var viewModel: WineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list, container, false)
        recVinos = v.findViewById(R.id.recViewList)

        viewModel = ViewModelProvider(requireActivity()).get(WineViewModel::class.java)

        return v
    }

    override fun onStart() {
        super.onStart()

       //viewModel.cargarDatos()
        viewModel.getListWines()

        recVinos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recVinos.layoutManager = linearLayoutManager

        vinoListAdapter = VinoListAdapter(viewModel.vinos) { x ->
            onItemClick(x)
        }

        recVinos.adapter = vinoListAdapter
    }

    fun onItemClick (position:Int ) : Boolean {


        var action: NavDirections  = ListFragmentDirections.actionListFragmentToDetailsFragment(position)

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