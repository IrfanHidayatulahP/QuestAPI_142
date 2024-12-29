package com.example.continuerestapi.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.continuerestapi.widget.MahasiswaApps

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(MahasiswaApps().container.mahasiswaRepository)
        }

        initializer {
            InsertViewModel(MahasiswaApps().container.mahasiswaRepository)
        }
    }
}

fun CreationExtras.mahasiswaApp() : MahasiswaApps =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApps)