package dadm.jmartor.paymate.data.users

import dadm.jmartor.paymate.data.users.model.UserDto
import retrofit2.Response

interface UserDataSource {
    suspend fun login(username: String, password: String) : Response<UserDto>

    suspend fun register(username: String, password: String) : Response<Unit>

    suspend fun getDebt(username: String, idGroup: Long) : Response<Double>

    suspend fun payDebt(username: String, idGroup: Long, quantity: Double) : Response<Unit>
}