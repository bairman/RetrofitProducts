package com.example.retrofitbair.presentetion.repository

import com.example.retrofitbair.presentetion.api.ProductApi
import com.example.retrofitbair.presentetion.data.AuthRequest
import com.example.retrofitbair.presentetion.data.Product
import com.example.retrofitbair.presentetion.data.Products
import com.example.retrofitbair.presentetion.data.User
import retrofit2.Response

class ApiRepository(private val productApi: ProductApi) {

    suspend fun repProdById(id: Int, token: String): Product {
        return productApi.getProductById(id, token)
    }

    suspend fun repAuth(authRequest: AuthRequest): Response<User> {
        return productApi.auth(authRequest)
    }

    suspend fun repAllProducts(token: String): Products {
        return productApi.getAllProducts(token)
    }

    suspend fun repProductsByName(token: String,name: String): Products {
        return productApi.getProductsByName(token, name)
    }
}