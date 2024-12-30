package com.example.continuerestapi.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.continuerestapi.model.Mahasiswa
import com.example.continuerestapi.repository.MahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UpdateUiState {
    object Success : UpdateUiState()
    object Error : UpdateUiState()
    object Loading : UpdateUiState()
    data class DataLoaded(val mahasiswa: Mahasiswa) : UpdateUiState()
}

class UpdateViewModel(
    private val repository: MahasiswaRepository,
    private val nim: String
) : ViewModel() {

    private val _updateUiState = MutableStateFlow<UpdateUiState>(UpdateUiState.Loading)
    val updateUiState: StateFlow<UpdateUiState> get() = _updateUiState

    private val _mahasiswaData = MutableStateFlow<Mahasiswa?>(null)
    val mahasiswaData: StateFlow<Mahasiswa?> get() = _mahasiswaData

    init {
        loadMahasiswaDetail()
    }

    /**
     * Memuat data mahasiswa untuk di-edit
     */
    private fun loadMahasiswaDetail() {
        viewModelScope.launch {
            _updateUiState.value = UpdateUiState.Loading
            try {
                val mahasiswa = repository.getMahasiswaById(nim)
                _mahasiswaData.value = mahasiswa
                _updateUiState.value = UpdateUiState.DataLoaded(mahasiswa)
            } catch (e: Exception) {
                _updateUiState.value = UpdateUiState.Error
            }
        }
    }

    /**
     * Memperbarui data mahasiswa yang sudah diedit
     */
    fun updateMahasiswaDetail(updatedMahasiswa: Mahasiswa) {
        viewModelScope.launch {
            _updateUiState.value = UpdateUiState.Loading
            try {
                repository.updateMahasiswa(nim, updatedMahasiswa)
                _updateUiState.value = UpdateUiState.Success
            } catch (e: Exception) {
                _updateUiState.value = UpdateUiState.Error
            }
        }
    }
}
