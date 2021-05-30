package com.example.vinos_app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.vinos_app.R
import com.example.vinos_app.viewModel.CreateUserViewModel
import com.example.vinos_app.viewModel.WineViewModel
import com.google.android.material.snackbar.Snackbar


class CreateUserFragment : Fragment() {

    lateinit var userName:EditText
    lateinit var userEmail: EditText
    lateinit var userPassword: TextView
    lateinit var buttonCreateUser: Button
    lateinit var v:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_create_user, container, false)
        userName = v.findViewById(R.id.userNameInput)
        userEmail = v.findViewById(R.id.emailUser)
        userPassword = v.findViewById(R.id.userPasswordInput)
        buttonCreateUser = v.findViewById(R.id.createUserButton)
        return v
    }

    lateinit var createUserViewModel: CreateUserViewModel


    override fun onStart() {
        super.onStart()
        createUserViewModel = ViewModelProvider(requireActivity()).get(CreateUserViewModel::class.java)
        buttonCreateUser.setOnClickListener(){

            createUserViewModel.addUser(userName.text.toString(), userEmail.text.toString(), userPassword.text.toString())
            Snackbar.make(v, "Usuario: " + userEmail.text + " "+ userName.text + " Password: "+userPassword.text, Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}