package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.users.ConnectivityChecker
import dadm.jmartor.paymate.data.users.model.toUnitDomain
import dadm.jmartor.paymate.utils.NoInternetException
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(var dataSource: GroupDataSource, var connectivityChecker: ConnectivityChecker): GroupRepository {

    override suspend fun create(name: String): Result<Unit> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.create(name).toUnitDomain()
        } else {
            Result.failure(NoInternetException())
        }
}