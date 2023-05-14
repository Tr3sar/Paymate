package dadm.jmartor.paymate.data.expenses

import dadm.jmartor.paymate.data.ConnectivityChecker
import dadm.jmartor.paymate.data.users.model.toUnitDomain
import dadm.jmartor.paymate.utils.NoInternetException
import toDomain
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject() constructor(
    var dataSource: ExpenseDataSource,
    var connectivityChecker: ConnectivityChecker) : ExpenseRepository {

    override suspend fun create(title: String, quantity: Double): Result<Unit> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.create(title, quantity).toUnitDomain()
        } else {
            Result.failure(NoInternetException())
        }

    override suspend fun addUsersFromGroupToExpense(groupId: Long, expenseId: Long): Result<Unit> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.addUsersFromGroupToExpense(groupId, expenseId).toUnitDomain()
        } else {
            Result.failure(NoInternetException())
        }

    override suspend fun getExpensesSize(): Result<Int> =
        if (connectivityChecker.isConnectionAvailable()) {
            dataSource.getExpensesSize().toDomain()
        } else {
            Result.failure(NoInternetException())
        }


}