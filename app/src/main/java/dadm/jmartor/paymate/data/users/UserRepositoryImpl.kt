package dadm.jmartor.paymate.data.users

import dadm.jmartor.paymate.data.users.model.toDomain
import dadm.jmartor.paymate.data.users.model.toUnitDomain
import dadm.jmartor.paymate.ui.domain.model.User
import dadm.jmartor.paymate.utils.NoInternetException
import javax.inject.Inject
import javax.sql.DataSource

class UserRepositoryImpl @Inject constructor(
    var dataSource: UserDataSource, var connectivityChecker: ConnectivityChecker) : UserRepository {
    override suspend fun login(username: String, password: String): Result<User> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.login(username, password).toDomain()
        } else {
            Result.failure(NoInternetException())
        }

    override suspend fun register(username: String, password: String): Result<Unit> =
        if (connectivityChecker.isConnectionAvailable()) {
             dataSource.register(username, password).toUnitDomain()
        } else {
             Result.failure(NoInternetException())
        }
}