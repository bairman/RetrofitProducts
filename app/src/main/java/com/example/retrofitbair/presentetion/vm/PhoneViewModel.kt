package com.example.retrofitbair.presentetion.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitbair.presentetion.data.AuthRequest
import com.example.retrofitbair.presentetion.data.Product
import com.example.retrofitbair.presentetion.repository.ApiRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PhoneViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    val token = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val image = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    val textPhnFrag1 = MutableLiveData<String>()
    val textPhnFrag2 = MutableLiveData<String>()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _productsSearch = MutableLiveData<List<Product>>()
    val productsSearch: LiveData<List<Product>> = _productsSearch

    fun getAuth(authRequest: AuthRequest) {
        viewModelScope.launch {
            delay(500)
            val result = apiRepository.repAuth(authRequest)
            val responseBody = result.body()
            if (result.isSuccessful && responseBody != null) {
                token.value = responseBody.token
                userName.value = result.body()?.username
                image.value = result.body()?.image
                error.value = result.errorBody()?.string()
                token.value = result.body()?.token
                Log.d("MyLog", "getAuth  ${token.value.toString()}")

            } else {
                error.value = result.errorBody()?.string()
            }
        }
    }
    fun getById(token1: String) {
        viewModelScope.launch {
            delay(500)
            val result1 = apiRepository.repProdById(1, token1)
            textPhnFrag1.value = result1.title
            textPhnFrag2.value = result1.description
            Log.d("MyLog", "getById and ${token.value.toString()}")
        }
    }
    fun getAllProd(token: String) {
        viewModelScope.launch {
            delay(500)
            val response = apiRepository.repAllProducts(token)
            _products.value = response.products
        }
    }
    fun getSearchProd(token: String, name: String) {
        viewModelScope.launch {
            delay(500)
            val response = apiRepository.repProductsByName(token, name)
            _productsSearch.value = response.products
        }
    }

}