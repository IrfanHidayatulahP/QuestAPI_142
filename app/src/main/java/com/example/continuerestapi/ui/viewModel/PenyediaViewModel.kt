package com.example.continuerestapi.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.continuerestapi.MahasiswaApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(MahasiswaApp().container.mahasiswaRepository)
        }

        initializer {
            InsertViewModel(MahasiswaApp().container.mahasiswaRepository)
        }
    }
}

fun CreationExtras.mahasiswaApp() : MahasiswaApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApp)