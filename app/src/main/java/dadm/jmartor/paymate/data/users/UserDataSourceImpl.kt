package dadm.jmartor.paymate.data.users

import android.util.Log
import dadm.jmartor.paymate.data.users.model.UserDto
import dadm.jmartor.paymate.ui.domain.model.User
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(var retrofit: Retrofit) : UserDataSource {

    private val retrofitUserService = retrofit.create(UserRetrofit::class.java)

    interface UserRetrofit {
        @GET("user/login")
        suspend fun login(@Query("name") username: String, @Query("password") password: String): Response<UserDto>

        @POST("user/register")
        suspend fun register(@Body userDto: UserDto): Response<Unit>

        @GET("user/debt_from_group")
        suspend fun getDebt(
            @Query("name") username: String,
            @Query("groupId") idGroup: Long
        ) : Response<Double>

        @PUT("user/pay")
        suspend fun payDebt(
            @Query("name") username: String,
            @Query("groupId") idGroup: Long,
            @Query("money") quantity: Double
        ) : Response<Unit>
    }

    override suspend fun login(username: String, password: String): Response<UserDto> =
        try{
            retrofitUserService.login(username, password)
        } catch (e: Exception) {
            Log.e("ERROR", "Error login UserDataSourceImpl")
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }


    override suspend fun register(username: String, password: String): Response<Unit> =
        try{
            val user: UserDto = UserDto(username, password)
            retrofitUserService.register(user)
        } catch (e: Exception) {
            Log.e("ERROR", "Error register UserDataSourceImpl")
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }

    override suspend fun getDebt(username: String, idGroup: Long): Response<Double> =
        try{
            retrofitUserService.getDebt(username, idGroup)
        } catch (e: Exception) {
            Log.e("ERROR", "Error getDebt UserDataSourceImpl")
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }


    override suspend fun payDebt(
        username: String,
        idGroup: Long,
        quantity: Double
    ): Response<Unit> =
        try{
            retrofitUserService.payDebt(username, idGroup, quantity)
        } catch (e: Exception) {
            Log.e("ERROR", "Error payDebt UserDataSourceImpl")
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
}