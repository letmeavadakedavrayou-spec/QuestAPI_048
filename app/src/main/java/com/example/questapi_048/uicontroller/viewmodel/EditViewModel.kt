package com.example.questapi_048.uicontroller.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_048.modeldata.DetailSiswa
import com.example.questapi_048.modeldata.UIStateSiswa
import com.example.questapi_048.modeldata.toDataSiswa
import com.example.questapi_048.modeldata.toDetailSiswa
import com.example.questapi_048.modeldata.toUiStateSiswa
import com.example.questapi_048.repositori.RepositoryDataSiswa
import com.example.questapi_048.uicontroller.route.DestinasiEdit
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[DestinasiEdit.idSiswa])

    init {
        viewModelScope.launch {
            uiStateSiswa = repositoryDataSiswa.getSatuSiswa(itemId).toUiStateSiswa(true)
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = true)
    }

    suspend fun updateSiswa() {
        if (uiStateSiswa.isEntryValid) {
            repositoryDataSiswa.updateSiswa(itemId, uiStateSiswa.detailSiswa.toDataSiswa())
        }
    }
}