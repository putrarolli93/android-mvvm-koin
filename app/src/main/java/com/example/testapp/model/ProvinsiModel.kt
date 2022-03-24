package com.example.testapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProvinsiModel(val provinsi: MutableList<Provinsi>): Parcelable

@Parcelize
data class Provinsi(val id: Int,
                    val nama: String): Parcelable