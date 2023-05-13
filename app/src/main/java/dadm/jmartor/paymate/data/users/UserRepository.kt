package dadm.jmartor.paymate.data.users

import dadm.jmartor.paymate.model.User

interface UserRepository {
    suspend fun login(username: String, password: String): Result<User>

    suspend fun register(username: String, password: String) : Result<Unit>
}