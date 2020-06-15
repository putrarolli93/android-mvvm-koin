package com.example.testapp.network

import com.example.testapp.model.ProvinsiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun getProvinsi(onResult: (isSuccess: Boolean, response: ProvinsiModel?) -> Unit) {
        ApiClient.instance.getProvinsi().enqueue(object : Callback<ProvinsiModel> {
            override fun onResponse(call: Call<ProvinsiModel>?, response: Response<ProvinsiModel>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<ProvinsiModel>?, t: Throwable?) {
                onResult(false, null)
            }
        })
    }

    companion object {
        private var INSTANCE: Repository? = null
        fun getInstance() = INSTANCE
            ?: Repository().also {
                INSTANCE = it
            }
    }
}