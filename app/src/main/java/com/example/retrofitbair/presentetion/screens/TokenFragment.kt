package com.example.retrofitbair.presentetion.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.retrofitbair.databinding.FragmentTokenBinding
import com.example.retrofitbair.presentetion.api.ProductApi
import com.example.retrofitbair.presentetion.data.AuthRequest
import com.example.retrofitbair.presentetion.vm.PhoneViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TokenFragment : Fragment() {
    private lateinit var binding: FragmentTokenBinding
    private val phoneViewModel: PhoneViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTokenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phoneViewModel.userName.observe(viewLifecycleOwner){
            binding.name.text = it
        }

        phoneViewModel.image.observe(viewLifecycleOwner){
              Picasso.get().load(it).into(binding.avatar)
        }
        phoneViewModel.error.observe(viewLifecycleOwner){
            binding.error.text = it
        }

        binding.apply {
            signIn.setOnClickListener {
                phoneViewModel.getAuth(
                    AuthRequest(
                        edLogin.text.toString(),
                        edPassword.text.toString()
                    )
                )
            }
        }

//        binding.apply {
//            signIn.setOnClickListener {
//                auth(
//                    AuthRequest(
//                        edLogin.text.toString(),
//                        edPassword.text.toString()
//                    )
//                )
//            }
//        }
    }

//    private fun auth(authRequest: AuthRequest) {
//        CoroutineScope(Dispatchers.IO).launch {
//            var responce = apiService.auth(authRequest)
//
//            requireActivity ().runOnUiThread {
//                val user = responce.body()
//                if (user != null) {
//                Picasso.get().load(user.image)
//                    .into(binding.avatar)
//                binding.name.text = user.firstName
//                        phoneViewModel.token.value = user.token
//            }
//                binding.error.text = responce.errorBody()?.string()
//            }
//        }
//    }

}


