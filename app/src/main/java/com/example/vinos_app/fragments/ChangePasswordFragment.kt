package com.example.vinos_app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.vinos_app.R
import com.example.vinos_app.viewModel.CreateUserViewModel


class ChangePasswordFragment : Fragment() {

    lateinit var passwordField: EditText
    lateinit var updatePaswordButton: Button
    lateinit var v : View

    lateinit var userViewModel: CreateUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity()).get(CreateUserViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_change_password, container, false)
        passwordField = v.findViewById(R.id.newPasswordField)
        updatePaswordButton= v.findViewById(R.id.updatePasswordButton)
        return v
    }

    override fun onStart() {
        super.onStart()

        updatePaswordButton.setOnClickListener(){
            Log.d("password","Password: "+passwordField.toString())
            userViewModel.updatePassword(passwordField.text.toString())

        }
    }

}