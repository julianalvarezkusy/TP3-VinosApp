package com.ort.tp3ejappv1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.vinos_app.R
import com.google.android.material.snackbar.Snackbar


class FragmentLogin : Fragment() {

    lateinit var v: View
    lateinit var inputUser: EditText
    lateinit var inputPassword: EditText
    lateinit var buttonLogin: Button

    var user : String = "fagen"
    var password : String = "123"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_login, container, false)

        inputUser =  v.findViewById(R.id.inputUser)
        inputPassword = v.findViewById(R.id.inputPassword)
        buttonLogin = v.findViewById(R.id.buttonLogin)

        return v
    }

    override fun onStart() {
        super.onStart()

        buttonLogin.setOnClickListener {

            if (inputUser.text.toString() == this.user && inputPassword.text.toString() == this.password) {

                Snackbar.make(v, "Se a iniciado sesion", Snackbar.LENGTH_SHORT).show()

                //val action = FragmentLoginDirections.actionFragmentLoginToListActivity()

                val action = FragmentLoginDirections.actionFragmentLoginToListFragment()



               v.findNavController().navigate(action)

            } else {
                Snackbar.make(v, "Error al ingresar ususario o contraseña", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

}