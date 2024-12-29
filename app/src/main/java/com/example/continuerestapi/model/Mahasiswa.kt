package com.example.continuerestapi.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,

    @SerialName("jenis_kelamin")
    val jenisKelamin: String,
    val kelas: String,
    val angkatan: String,
    )
