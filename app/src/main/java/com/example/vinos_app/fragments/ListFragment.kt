package com.example.vinos_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinos_app.R
import com.example.vinos_app.adapters.VinoListAdapter
import com.example.vinos_app.entities.Vino


class ListFragment : Fragment() {

    lateinit var v:View



    var vinos : MutableList<Vino> = ArrayList()

    lateinit var recVinos: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var vinoListAdapter : VinoListAdapter


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

        return v
    }

    override fun onStart() {
        super.onStart()

        vinos.add(Vino("Estrella 1977", 1500.00, 4.8, "Weinert"))
        vinos.add(Vino("La Violeta 2012", 2000.00, 4.8, "Monteviejo"))
        vinos.add(Vino("Nosotros 2010", 1300.00, 4.7, "Susana Balbo"))
        vinos.add(Vino("Chañares", 2005.00, 4.7, "Mendoza"))

        recVinos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recVinos.layoutManager = linearLayoutManager

        vinoListAdapter = VinoListAdapter(vinos)

        recVinos.adapter = vinoListAdapter
    }

}