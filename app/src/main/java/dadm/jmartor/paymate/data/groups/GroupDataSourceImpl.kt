package dadm.jmartor.paymate.data.groups

import android.util.Log
import dadm.jmartor.paymate.data.groups.model.ExpenseDto
import dadm.jmartor.paymate.data.groups.model.GroupDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject

class GroupDataSourceImpl @Inject constructor(var retrofit: Retrofit): GroupDataSource {

    private val retrofitGroupService = retrofit.create(GroupRetrofit::class.java)

    interface GroupRetrofit{

        @POST("group/create")
        suspend fun create(@Body groupDto: GroupDto): Response<Unit>

        @GET("group/get_expenses")
        suspend fun getExpensesFromGroup(@Query("groupId") groupId: Long) : Response<List<ExpenseDto>>
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

    override suspend fun getExpensesFromGroup(groupId: Long): Response<List<ExpenseDto>> =
        try{
            retrofitGroupService.getExpensesFromGroup(groupId)
        }catch (e:Exception){
            Log.e("ERROR", "Error getExpensesFromGroup GroupDataSourceImpl")
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }

}