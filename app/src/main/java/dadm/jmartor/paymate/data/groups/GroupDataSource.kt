package dadm.jmartor.paymate.data.groups

import retrofit2.Response

interface GroupDataSource {

    suspend fun create(name: String) : Response<Unit>

}