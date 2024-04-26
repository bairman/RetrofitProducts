package com.example.retrofitbair.presentetion.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitbair.databinding.FragmentSearchBinding
import com.example.retrofitbair.presentetion.adapter.SearchAdapter
import com.example.retrofitbair.presentetion.api.ProductApi
import com.example.retrofitbair.presentetion.vm.PhoneViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    val apiService: ProductApi by inject()
    private val phoneViewModel: PhoneViewModel by activityViewModels()
    private lateinit var adapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchAdapter()
        binding.rcVieww.layoutManager = LinearLayoutManager(requireContext())
        binding.rcVieww.adapter = adapter

        binding.serachView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                phoneViewModel.token.observe(viewLifecycleOwner) { token ->
                    CoroutineScope(Dispatchers.IO).launch {
                        delay(500)
                        val request = newText?.let {
                            apiService.getProductsByName(
                                token,
                                it
                            )
                        }
                        requireActivity().runOnUiThread {
                            adapter.submitList(request?.products)
                        }
                    }
                }
                return true
            }
        })
    }
}


//kminchelle
//0lelplR