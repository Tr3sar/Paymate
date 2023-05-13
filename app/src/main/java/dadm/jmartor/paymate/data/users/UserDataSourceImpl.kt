package dadm.jmartor.paymate.data.users

import android.util.Log
import dadm.jmartor.paymate.data.users.model.UserDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(var retrofit: Retrofit) : UserDataSource {

    private val retrofitUserService = retrofit.create(UserRetrofit::class.java)

    interface UserRetrofit {
        @GET("user/login")
        suspend fun login(@Query("name") username: String, @Query("password") password: String): Response<UserDto>

        @POST("user/register")
        suspend fun register(@Body userDto: UserDto): Response<Unit>
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
}