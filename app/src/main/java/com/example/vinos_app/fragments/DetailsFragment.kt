package com.example.vinos_app.fragments

import WineViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.vinos_app.R

class DetailsFragment : Fragment() {

    lateinit var v: View


    lateinit var wineName: TextView
    lateinit var wineCellar: TextView
    lateinit var wineRating: TextView

    private lateinit var viewModel: WineViewModel

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

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(WineViewModel::class.java)
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

}
