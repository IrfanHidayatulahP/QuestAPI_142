package com.example.continuerestapi.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.continuerestapi.MahasiswaApplication

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(mahasiswaApplication().container.mahasiswaRepository)
        }
        initializer {
            InsertViewModel(mahasiswaApplication().container.mahasiswaRepository)
        }
    }
}

fun CreationExtras.mahasiswaApplication() : MahasiswaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplication)