package dadm.jmartor.paymate.data.expenses

import retrofit2.Response

interface ExpenseDataSource {
    suspend fun create(title: String, quantity: Double) : Response<Unit>

    suspend fun addUsersFromGroupToExpense(groupId: Long, expenseId: Long) : Response<Unit>
}