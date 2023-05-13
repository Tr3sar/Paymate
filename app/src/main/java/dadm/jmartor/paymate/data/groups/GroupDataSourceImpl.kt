package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.groups.model.GroupDto
import dadm.jmartor.paymate.data.users.UserDataSourceImpl
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

class GroupDataSourceImpl @Inject constructor(var retrofit: Retrofit): GroupDataSource {

    private val retrofitGroupService = retrofit.create(GroupRetrofit::class.java)

    interface GroupRetrofit{

        @POST("group/create")
        suspend fun create(@Body groupDto: GroupDto): Response<Unit>
    }

    override suspend fun create(name: String): Response<Unit> =
        val group: GroupDto = GroupDto(name)
        retrofitGroupService.create(name)

}