package com.example.questapi_048.uicontroller.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_048.modeldata.DataSiswa
import com.example.questapi_048.repositori.RepositoryDataSiswa
import com.example.questapi_048.uicontroller.route.DestinasiDetail
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DetailUiState {
    data class Success(val siswa: DataSiswa) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    private val _idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetail.idSiswa])
    var detailUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    init {
        getSatuSiswa()
    }

    fun getSatuSiswa() {
        viewModelScope.launch {
            detailUiState = DetailUiState.Loading
            detailUiState = try {
                DetailUiState.Success(repositoryDataSiswa.getSatuSiswa(_idSiswa))
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: Exception) {
                DetailUiState.Error
            }
        }
    }

    fun deleteSiswa() {
        viewModelScope.launch {
            try {
                repositoryDataSiswa.deleteSiswa(_idSiswa)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}