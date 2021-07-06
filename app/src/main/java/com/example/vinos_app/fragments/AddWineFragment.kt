package com.example.vinos_app.fragments

import WineViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vinos_app.R
import com.example.vinos_app.entities.Vino


class AddWineFragment : Fragment() {

    lateinit var wineName : EditText
    lateinit var wineCellar : EditText
    lateinit var winePrice: EditText
    lateinit var wineRating: RatingBar
    lateinit var saveButton: Button
    lateinit var v: View
    lateinit var wineViewModel: WineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_wine, container, false)
        wineName = v.findViewById(R.id.nameAddWine)
        wineCellar= v.findViewById(R.id.nameAddCellar)
        winePrice= v.findViewById(R.id.priceAddWine)
        wineRating = v.findViewById(R.id.ratingAddWine)
        saveButton = v.findViewById(R.id.buttonAddWine)



        return v
    }

    override fun onStart() {
        super.onStart()


        wineViewModel = ViewModelProvider(requireActivity()).get(WineViewModel::class.java)
        saveButton.setOnClickListener{


            var vino = Vino(wineName.text.toString(), winePrice.text.toString().toDouble(), wineRating.rating.toDouble(),wineCellar.text.toString())

            var vinoGuardado = wineViewModel.uploadWine(vino)







            val action = AddWineFragmentDirections.actionAddWineFragmentToListFragment()
            v.findNavController().navigate(action)
        }
    }




}