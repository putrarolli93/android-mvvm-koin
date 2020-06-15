package com.example.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.testapp.model.Provinsi
import com.example.testapp.network.Repository
import com.example.testapp.viewmodel.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    val listProvinsi = MutableLiveData<List<Provinsi>>()

    fun getProvinsi() {
        dataLoading.value = true
        Repository.getInstance().getProvinsi() { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                listProvinsi.value = response?.provinsi
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }


}