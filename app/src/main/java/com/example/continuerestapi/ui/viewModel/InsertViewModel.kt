package com.example.continuerestapi.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.continuerestapi.model.Mahasiswa
import com.example.continuerestapi.repository.MahasiswaRepository

class InsertViewModel(private val mhs : MahasiswaRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
}



fun Mahasiswa.toUiStateMhs() : InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Mahasiswa.toInsertUiEvent() : InsertUiEvent = InsertUiEvent(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)