package com.example.continuerestapi.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.continuerestapi.repository.MahasiswaApplication

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(mahasiswaApplication().container.mahasiswaRepository)
        }
        initializer {
            InsertViewModel(mahasiswaApplication().container.mahasiswaRepository)
        }
        initializer {
            val savedStateHandle = createSavedStateHandle()
            val nim = savedStateHandle.get<String>("nim") ?: throw IllegalArgumentException("NIM is required")
            DetailViewModel(
                mahasiswaApplication().container.mahasiswaRepository,
                nim
            )
        }
        initializer {
            val savedStateHandle = createSavedStateHandle()
            val nim = savedStateHandle.get<String>("nim") ?: throw IllegalArgumentException("NIM is required")
            UpdateViewModel(
                mahasiswaApplication().container.mahasiswaRepository,
                nim
            )
        }
    }
}

fun CreationExtras.mahasiswaApplication() : MahasiswaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplication)