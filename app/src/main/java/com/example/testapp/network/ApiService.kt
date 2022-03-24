package com.example.testapp.network

import com.example.testapp.model.ProvinsiModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("daerahindonesia/provinsi")
    fun getProvinsi(): Call<ProvinsiModel>

    @GET("daerahindonesia/provinsi")
    fun getProvinsiFlowable(): Observable<ProvinsiModel>

}