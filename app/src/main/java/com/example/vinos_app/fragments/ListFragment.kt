package com.example.vinos_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.vinos_app.R
import com.ort.tp3ejappv1.fragments.FragmentLoginDirections


class ListFragment : Fragment() {

    lateinit var v:View
    lateinit var returnButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_login, container, false)
        returnButton = v.findViewById(R.id.returnButton)
        return v
    }

    override fun onStart() {
        super.onStart()
        returnButton.setOnClickListener(){
            val action = ListFragmentDirections.actionListFragment2ToFragmentLogin2()
            v.findNavController().navigate(action)
        }
    }

}