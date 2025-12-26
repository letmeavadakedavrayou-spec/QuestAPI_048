package com.example.questapi_048.repositori

import com.example.questapi_048.apiservice.ServiceApiSiswa
import com.example.questapi_048.modeldata.DataSiswa


interface RepositoryDataSiswa {
    suspend fun getDataSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void>
    suspend fun getSatuSiswa(id: Int): DataSiswa
    suspend fun updateSiswa(id: Int, dataSiswa: DataSiswa): retrofit2.Response<Void>
    suspend fun deleteSiswa(id: Int): retrofit2.Response<Void>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoryDataSiswa {
    override suspend fun getDataSiswa(): List<DataSiswa> = serviceApiSiswa.getSiswa()
    override suspend fun postDataSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void> = serviceApiSiswa.postSiswa(dataSiswa)
    override suspend fun getSatuSiswa(id: Int): DataSiswa = serviceApiSiswa.getSatuSiswa(id)
    override suspend fun updateSiswa(id: Int, dataSiswa: DataSiswa): retrofit2.Response<Void> = serviceApiSiswa.editSatuSiswa(id, dataSiswa)
    override suspend fun deleteSiswa(id: Int): retrofit2.Response<Void> = serviceApiSiswa.hapusSatuSiswa(id)
}