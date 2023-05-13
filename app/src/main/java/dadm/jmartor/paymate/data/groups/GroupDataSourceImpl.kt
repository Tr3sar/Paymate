package dadm.jmartor.paymate.data.groups

import android.util.Log
import dadm.jmartor.paymate.data.groups.model.GroupDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

class GroupDataSourceImpl @Inject constructor(var retrofit: Retrofit): GroupDataSource {

    private val retrofitGroupService = retrofit.create(GroupRetrofit::class.java)

    interface GroupRetrofit{

        @POST("create")
        suspend fun create(@Body groupDto: GroupDto): Response<Unit>
    }

    override suspend fun create(name: String): Response<Unit> =
        try{
            val group: GroupDto = GroupDto(name)
            retrofitGroupService.create(group)
        }catch (e:Exception){
            Log.e("ERROR", "Error create GroupDataSourceImpl")
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }

}