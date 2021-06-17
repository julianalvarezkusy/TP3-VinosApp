package com.example.vinos_app.fragments

import WineViewModel
import android.os.Bundle
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
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class ListFragment : Fragment() {

    lateinit var v: View

    lateinit var recVinos: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var vinoListAdapter: VinoListAdapter

    private lateinit var viewModel: WineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        vinoListAdapter = VinoListAdapter{ x -> onItemClick(x) }
        viewModel = ViewModelProvider(requireActivity()).get(WineViewModel::class.java)

        val parentJob = Job()
        val scope = CoroutineScope(Dispatchers.Default + parentJob)

        scope.launch {
            //viewModel.cargarDatos()
            viewModel.getListWines()
        }
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

        viewModel.vinosLiveData.observe(viewLifecycleOwner, Observer { result ->
            vinoListAdapter.setData(result)
            recVinos.adapter = vinoListAdapter
            //vinoListAdapter.filter("m")
        })

    }

    private fun onItemClick (position:Int ) : Boolean {

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
}