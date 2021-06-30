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
import androidx.navigation.findNavController
import com.example.vinos_app.R
import com.example.vinos_app.viewModel.CreateUserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*


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

            val parentJob = Job()
            val scope = CoroutineScope(Dispatchers.Default + parentJob)

            scope.launch {
                val createUser = async{createUserViewModel.addUser(userName.text.toString(), userEmail.text.toString(), userPassword.text.toString())}
                val userCreated = createUser.await()


                val action = CreateUserFragmentDirections.actionCreateUserFragmentToFragmentLogin()

                v.findNavController().navigate(action)
                Log.d("USER", "USER CREATED: " + userCreated)
                if(userCreated){
                    Snackbar.make(v, "Usuario creado con éxito", Snackbar.LENGTH_SHORT)
                            .show()
                }else{
                    Snackbar.make(v, "Error en la creación del usuario", Snackbar.LENGTH_SHORT)
                            .show()
                }
            }
            //val userCreated = createUserViewModel.addUser(userName.text.toString(), userEmail.text.toString(), userPassword.text.toString())




        }
    }
}