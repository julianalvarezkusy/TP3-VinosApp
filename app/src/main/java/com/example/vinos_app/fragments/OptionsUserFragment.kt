package com.example.vinos_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vinos_app.R
import com.example.vinos_app.viewModel.CreateUserViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class OptionsUserFragment : Fragment() {

    lateinit var v: View

    lateinit var userViewModel: CreateUserViewModel

    lateinit var userEmail: TextView
    lateinit var userName: TextView

    lateinit var buttonLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity()).get(CreateUserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_options_user, container, false)

        userName = v.findViewById(R.id.nameUserOptions )
        userEmail = v.findViewById(R.id.emailUserOptions)

        buttonLogout = v.findViewById(R.id.buttonLogout)


        return v
    }

    override fun onStart() {
        super.onStart()

        userEmail
        userName

        buttonLogout.setOnClickListener {
            Firebase.auth.signOut()

            val action = OptionsUserFragmentDirections.actionOptionsUserFragmentToLoginActivity()
            v.findNavController().navigate(action)
        }
    }


        }