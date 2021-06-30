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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import java.lang.Exception


class OptionsUserFragment : Fragment() {

    lateinit var v: View

    lateinit var userViewModel: CreateUserViewModel

    lateinit var userEmail: TextView
    lateinit var userName: TextView

    lateinit var buttonLogout: Button
    lateinit var buttonFavourites : Button

    lateinit var buttonChangePassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(requireActivity()).get(CreateUserViewModel::class.java)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

        buttonFavourites = v.findViewById(R.id.buttonFavourites)

        buttonChangePassword = v.findViewById(R.id.buttonChangePassword)





        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Obtengo datos del User

        try {
            val parentJob = Job()
            val scope = CoroutineScope(Dispatchers.Default + parentJob)


            scope.launch {
                val getUser = async {
                    userViewModel.getAppUserConected()
                }
                val user = getUser.await()
                withContext(Dispatchers.Main){
                    if (user != null) {
                        userEmail.text = "Mail:" + user.email
                        userName.text = "Nombre:" + user.name
                    } else {
                        userEmail.text = "User not found"
                        userName.text = "User not Found"
                    }
                }



            }

        }catch (e: Exception){
            Log.d("Error", "Error: "+e.message)
        }



    }

    override fun onStart() {
        super.onStart()






        buttonLogout.setOnClickListener {
            Firebase.auth.signOut()

            val action = OptionsUserFragmentDirections.actionOptionsUserFragmentToLoginActivity()
            v.findNavController().navigate(action)
        }

        buttonFavourites.setOnClickListener{
            val action = OptionsUserFragmentDirections.actionOptionsUserFragmentToFavouritesListFragment()
            v.findNavController().navigate(action)
        }

        buttonChangePassword.setOnClickListener{
            val action = OptionsUserFragmentDirections.actionOptionsUserFragmentToChangePasswordFragment()
            v.findNavController().navigate(action)
        }


    }


        }