package dadm.jmartor.paymate.data.users

import dadm.jmartor.paymate.model.Debt
import dadm.jmartor.paymate.ui.domain.model.User
import retrofit2.Response

interface UserRepository {
    suspend fun login(username: String, password: String): Result<User>

    suspend fun register(username: String, password: String) : Result<Unit>

    suspend fun getDebt(username: String, idGroup: Long) : Result<Double>

    suspend fun payDebt(username: String, idGroup: Long, quantity: Double) : Result<Unit>
}