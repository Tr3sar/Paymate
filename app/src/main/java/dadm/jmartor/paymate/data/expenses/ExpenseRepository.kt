package dadm.jmartor.paymate.data.expenses

import retrofit2.Response

interface ExpenseRepository {
    suspend fun create(title: String, quantity: Double) : Result<Unit>

    suspend fun addUsersFromGroupToExpense(groupId: Long, expenseId: Long) : Result<Unit>

    suspend fun getExpensesSize() : Result<Int>
}