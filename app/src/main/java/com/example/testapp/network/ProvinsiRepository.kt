package com.example.testapp.network

import com.example.testapp.model.ProvinsiModel
import io.reactivex.rxjava3.core.Observable

class ProvinsiRepository(private val service: ApiService) {

    fun getProvinsi(): Observable<ProvinsiModel> {
        return service.getProvinsiFlowable()
    }

}
