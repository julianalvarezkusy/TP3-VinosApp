package com.example.vinos_app.fragments

import WineViewModel
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinos_app.R
import com.example.vinos_app.adapters.VinoListAdapter
import com.example.vinos_app.viewModel.CreateUserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class ListFragment : Fragment() {

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

/*        val parentJob = Job()
        val scope = CoroutineScope(Dispatchers.Default + parentJob)

        scope.launch {
            //viewModel.cargarDatos()
            wineViewModel.getListWines()
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list, container, false)
        setHasOptionsMenu(true)
        recVinos = v.findViewById(R.id.recViewList)

        return v
    }

    override fun onStart() {
        super.onStart()

        recVinos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recVinos.layoutManager = linearLayoutManager

        //Cargo la lista inicial
        wineViewModel.getListWines()
        wineViewModel.vinosLiveData.observe(viewLifecycleOwner, Observer { result ->
            Log.d("ListFragment", "Error" + result.toString())
            vinoListAdapter.setData(result)
            recVinos.adapter = vinoListAdapter

        })

    }

    private fun onItemsClick(position: Int,item: String): Boolean{
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_wine_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.app_bar_search)
        if (searchItem!= null){
            val searchView = searchItem.actionView as SearchView
            searchView.maxWidth = Integer.MAX_VALUE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    vinoListAdapter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    vinoListAdapter.filter(newText)
                    return false
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if(id == R.id.item_options_user){

            var action = ListFragmentDirections.actionListFragmentToOptionsUserFragment()
            v.findNavController().navigate(action)

        }

        return true
    }
}