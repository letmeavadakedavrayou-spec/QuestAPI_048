package com.example.questapi_048.uicontroller.route

import com.example.questapi_048.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa
    const val idSiswa = "itemId"
    val routeWithArgs = "$route/{$idSiswa}"
}