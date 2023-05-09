package dadm.jmartor.paymate.data.users

import dadm.jmartor.paymate.ui.domain.model.User

interface UserRepository {
    suspend fun login(username: String, password: String): Result<User>
}