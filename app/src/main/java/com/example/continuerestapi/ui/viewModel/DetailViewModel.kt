package com.example.continuerestapi.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.continuerestapi.model.Mahasiswa
import com.example.continuerestapi.repository.MahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val mahasiswaRepository: MahasiswaRepository
) : ViewModel() {

    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    fun getMahasiswaDetail(nim: String) {
        viewModelScope.launch {
            _detailUiState.update { it.copy(isLoading = true, isError = false, errorMessage = "") }
            try {
                val mahasiswa = mahasiswaRepository.getMahasiswaById(nim)
                _detailUiState.update {
                    it.copy(
                        detailUiEvent = mahasiswa.toDetailUiEvent(),
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _detailUiState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = e.message ?: "Unknown Error"
                    )
                }
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertUiEvent()
}

fun Mahasiswa.toDetailUiEvent() : InsertUiEvent {
    return InsertUiEvent(
        nim = nim,
        nama = nama,
        alamat = alamat,
        jenisKelamin = jenisKelamin,
        kelas = kelas,
        angkatan = angkatan
    )
}