package dadm.jmartor.paymate.data.users

import dadm.jmartor.paymate.data.ConnectivityChecker
import dadm.jmartor.paymate.data.users.model.*
import dadm.jmartor.paymate.model.User
import dadm.jmartor.paymate.utils.NoInternetException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    var dataSource: UserDataSource, var connectivityChecker: ConnectivityChecker
) : UserRepository {
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

    override suspend fun getDebt(username: String, idGroup: Long): Result<Double> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.getDebt(username, idGroup).toDoubleDomain()
        } else {
            Result.failure(NoInternetException())
        }

    override suspend fun payDebt(
        username: String,
        idGroup: Long,
        quantity: Double
    ): Result<Unit> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.payDebt(username, idGroup, quantity).toUnitDomain()
        } else {
            Result.failure(NoInternetException())
        }

    override suspend fun getAllUsers(): Result<List<User>> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.getAllUsers().toDomainList()
        } else {
            Result.failure(NoInternetException())
        }
}