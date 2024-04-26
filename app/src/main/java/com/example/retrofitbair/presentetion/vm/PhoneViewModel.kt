package com.example.retrofitbair.presentetion.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneViewModel: ViewModel() {
    val token = MutableLiveData<String>()
    
}