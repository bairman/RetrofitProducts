package com.example.retrofitbair.presentetion.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitbair.presentetion.api.ProductApi
import com.example.retrofitbair.presentetion.adapter.PhoneAdapter
import com.example.retrofitbair.databinding.FragmentProductsBinding
import com.example.retrofitbair.presentetion.vm.PhoneViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductsBinding
    private val phoneViewModel: PhoneViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PhoneAdapter()
        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
        binding.rcView.adapter = adapter
        phoneViewModel.token.observe(viewLifecycleOwner) { token ->
            phoneViewModel.getAllProd(token)
        }
        phoneViewModel.products.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
