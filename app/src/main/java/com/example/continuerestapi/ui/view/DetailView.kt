package com.example.continuerestapi.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.continuerestapi.ui.viewModel.DetailUiState
import com.example.continuerestapi.ui.viewModel.DetailViewModel
import com.example.continuerestapi.ui.viewModel.PenyediaViewModel
import com.example.continuerestapi.widget.CostumeTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    nim: String,
    onNavigateBack: () -> Unit,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: () -> Unit,
    onDeleteSuccess: () -> Unit // Navigasi setelah delete berhasil
) {
    val detailUiState by viewModel.detailUiState.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Detail Mahasiswa",
                canNavigateBack = true,
                navigateUp = onNavigateBack,
                onRefresh = { /* Aksi refresh khusus halaman detail jika diperlukan */ }
            )
        }
    ) { paddingValues ->
        when (detailUiState) {
            is DetailUiState.Loading -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            is DetailUiState.Success -> {
                val mahasiswa = (detailUiState as DetailUiState.Success).mahasiswa
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Nama: ${mahasiswa.nama}", style = MaterialTheme.typography.titleLarge)
                    Text(text = "NIM: ${mahasiswa.nim}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Kelas: ${mahasiswa.kelas}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Jenis Kelamin: ${mahasiswa.jenisKelamin}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Alamat: ${mahasiswa.alamat}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Angkatan: ${mahasiswa.angkatan}", style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tombol Edit
                    Button(
                        onClick = onEditClick,
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Edit")
                    }

                    // Tombol Delete
                    Button(
                        onClick = { showDeleteDialog = true },
                        shape = MaterialTheme.shapes.small,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Delete")
                    }
                }
            }

            is DetailUiState.Error -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Gagal memuat data. Silakan coba lagi.")
            }
        }
    }

    // Dialog Konfirmasi Delete
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Hapus Data") },
            text = { Text("Apakah Anda yakin ingin menghapus data ini?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteMahasiswa(nim)
                        showDeleteDialog = false
                        onDeleteSuccess()
                    }
                ) {
                    Text("Ya, Hapus", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }
}
