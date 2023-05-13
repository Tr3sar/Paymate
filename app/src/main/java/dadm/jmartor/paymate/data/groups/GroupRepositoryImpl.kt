package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.groups.model.toDomainList
import dadm.jmartor.paymate.data.groups.model.toUnitDomain
import dadm.jmartor.paymate.model.Group
import dadm.jmartor.paymate.utils.NoInternetException
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(var dataSource: GroupDataSource, var connectivityChecker: ConnectivityChecker): GroupRepository {

    override suspend fun create(name: String): Result<Unit> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.create(name).toUnitDomain()
        } else {
            Result.failure(NoInternetException())
        }

    override suspend fun addUser(groupId: Long, name: String): Result<Unit> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.addUser(groupId, name).toUnitDomain()
        } else {
            Result.failure(NoInternetException())
        }

    override suspend fun getMyGroups(name: String): Result<List<Group>> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.getMyGroups(name).toDomainList()
        } else {
            Result.failure(NoInternetException())
        }
}