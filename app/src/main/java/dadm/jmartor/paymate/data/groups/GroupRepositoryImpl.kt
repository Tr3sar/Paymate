package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.users.ConnectivityChecker
import dadm.jmartor.paymate.data.users.model.toDomainList
import dadm.jmartor.paymate.data.users.model.toUnitDomain
import dadm.jmartor.paymate.ui.domain.model.User
import dadm.jmartor.paymate.utils.NoInternetException
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(var dataSource: GroupDataSource, var connectivityChecker: ConnectivityChecker): GroupRepository {

    override suspend fun create(name: String): Result<Unit> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.create(name).toUnitDomain()
        } else {
            Result.failure(NoInternetException())
        }

    override suspend fun getUsers(groupId: Long): Result<List<User>> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.getUsers(groupId).toDomainList()
        } else {
            Result.failure(NoInternetException())
        }
}