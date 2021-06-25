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
import androidx.navigation.findNavController
import com.example.vinos_app.R
import com.example.vinos_app.entities.User
import com.example.vinos_app.viewModel.CreateUserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class LoginFragment: Fragment() {

    lateinit var v: View

    lateinit var inputName: EditText
    lateinit var inputPassword: EditText
    lateinit var buttonLogin: Button
    lateinit var buttonCreateUser : Button
    var users : MutableList<User> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_login, container, false)
        inputName = v.findViewById(R.id.inputUser)
        inputPassword = v.findViewById(R.id.inputPassword)
        buttonLogin = v.findViewById(R.id.buttonLogin)
        buttonCreateUser = v.findViewById(R.id.buttonCreateUser)

        return v
    }

    override fun onStart() {
        super.onStart()
        //users.add(User("user1", "user1@hotmail.com", "1"))
        //users.add(User("user2", "user2@hotmail.com", "2"))
<<<<<<< HEAD
        //users.add(User("user3", "user3@hotmail.com", "3"))
=======
       // users.add(User("user3", "user3@hotmail.com", "3"))
>>>>>>> da1a4ec3e55e975cf1dd668a9bf3da4235f8fe50

        lateinit var createUserViewModel: CreateUserViewModel

        createUserViewModel = ViewModelProvider(requireActivity()).get(CreateUserViewModel::class.java)


        buttonLogin.setOnClickListener {

            val parentJob = Job()
            val scope = CoroutineScope(Dispatchers.Default + parentJob)

            scope.launch {
                val getUser = async{createUserViewModel.getUser(inputName.text.toString(), inputPassword.text.toString())}
                if (getUser.await()) {


                    val action = LoginFragmentDirections.actionFragmentLoginToActivityList()

                    v.findNavController().navigate(action)

                } else {
                    Snackbar.make(v, "Error al ingresar ususario o contraseña", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }



        }

        buttonCreateUser.setOnClickListener(){

            val action = LoginFragmentDirections.actionFragmentLoginToCreateUserFragment()

            v.findNavController().navigate(action)
        }

    }
}
