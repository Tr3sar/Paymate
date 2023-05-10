package dadm.jmartor.paymate.data.users

import dadm.jmartor.paymate.ui.domain.model.User
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor() : UserDataSource {

    override suspend fun login(username: String, password: String): Result<User> {
        val userLogged = User("Josep", "contra")
        return Result.success(userLogged)
    }

    override suspend fun register(username: String, password: String): Result<Unit> {
        return Result.success(Unit)
    }
}