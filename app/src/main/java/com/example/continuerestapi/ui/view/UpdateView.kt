package com.example.continuerestapi.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.continuerestapi.model.Mahasiswa
import com.example.continuerestapi.ui.viewModel.UpdateUiState
import com.example.continuerestapi.ui.viewModel.UpdateViewModel
import com.example.continuerestapi.ui.viewModel.PenyediaViewModel
import com.example.continuerestapi.widget.CostumeTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    nim: String,
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit, // Fungsi untuk navigasi ke HomeView
    viewModel: UpdateViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val updateUiState by viewModel.updateUiState.collectAsState()
    val mahasiswaData by viewModel.mahasiswaData.collectAsState()

    var nama by remember { mutableStateOf("") }
    var kelas by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var angkatan by remember { mutableStateOf("") }

    // Memuat data mahasiswa saat pertama kali diakses
    LaunchedEffect(mahasiswaData) {
        mahasiswaData?.let { mahasiswa ->
            nama = mahasiswa.nama
            kelas = mahasiswa.kelas
            jenisKelamin = mahasiswa.jenisKelamin
            alamat = mahasiswa.alamat
            angkatan = mahasiswa.angkatan
        }
    }

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Edit Mahasiswa",
                canNavigateBack = true,
                navigateUp = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (updateUiState is UpdateUiState.Loading) {
                CircularProgressIndicator()
            } else {
                OutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = { Text("Nama") }
                )
                OutlinedTextField(
                    value = kelas,
                    onValueChange = { kelas = it },
                    label = { Text("Kelas") }
                )
                OutlinedTextField(
                    value = jenisKelamin,
                    onValueChange = { jenisKelamin = it },
                    label = { Text("Jenis Kelamin") }
                )
                OutlinedTextField(
                    value = alamat,
                    onValueChange = { alamat = it },
                    label = { Text("Alamat") }
                )
                OutlinedTextField(
                    value = angkatan,
                    onValueChange = { angkatan = it },
                    label = { Text("Angkatan") }
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Tombol Simpan Perubahan
                Button(
                    onClick = {
                        val updatedMahasiswa = Mahasiswa(
                            nim = nim,
                            nama = nama,
                            kelas = kelas,
                            jenisKelamin = jenisKelamin,
                            alamat = alamat,
                            angkatan = angkatan
                        )
                        viewModel.updateMahasiswaDetail(updatedMahasiswa)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Simpan Perubahan")
                }

                // Navigasi ke Home saat update berhasil
                when (updateUiState) {
                    is UpdateUiState.Success -> {
                        LaunchedEffect(Unit) {
                            onNavigateToHome()
                        }
                        Text("Data berhasil diperbarui!", color = MaterialTheme.colorScheme.primary)
                    }
                    is UpdateUiState.Error -> {
                        Text("Gagal memperbarui data.", color = MaterialTheme.colorScheme.error)
                    }
                    else -> {}
                }
            }
        }
    }
}
