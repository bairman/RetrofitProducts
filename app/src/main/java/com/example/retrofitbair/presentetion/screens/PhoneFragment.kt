package com.example.retrofitbair.presentetion.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.retrofitbair.presentetion.api.ProductApi
import com.example.retrofitbair.databinding.FragmentPhoneBinding
import com.example.retrofitbair.presentetion.vm.PhoneViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PhoneFragment : Fragment() {
    private lateinit var binding: FragmentPhoneBinding
    val apiService: ProductApi by inject()
    private val phoneViewModel: PhoneViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyLog", "getAuth  ${phoneViewModel.token.value.toString()}")

        phoneViewModel.token.observe(viewLifecycleOwner) { token ->
            phoneViewModel.getById(token)
            Log.d("MyLog", "PhoneFragment ${token}")
        }

        phoneViewModel.textPhnFrag1.observe(viewLifecycleOwner) {
            binding.text1.text = it
        }
        phoneViewModel.textPhnFrag2.observe(viewLifecycleOwner) {
            binding.text2.text = it
        }
    }

}

//
//        phoneViewModel.token.observe(viewLifecycleOwner){token ->
//            CoroutineScope(Dispatchers.IO).launch {
//                val request = apiService.getProductById(1, token)
//                withContext(Dispatchers.Main){
//                    binding.text1.text = request.brand
//                    binding.text2.text  = request.description
//
//                }
//            }
//        }