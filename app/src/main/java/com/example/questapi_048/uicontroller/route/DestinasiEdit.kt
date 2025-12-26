package com.example.questapi_048.uicontroller.route

import com.example.questapi_048.R

object DestinasiEdit : DestinasiNavigasi {
    override val route = "edit_siswa"
    override val titleRes = R.string.edit_siswa
    const val idSiswa = "itemId"
    val routeWithArgs = "$route/{$idSiswa}"
}