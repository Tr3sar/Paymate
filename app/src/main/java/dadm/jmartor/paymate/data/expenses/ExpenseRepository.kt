package dadm.jmartor.paymate.data.expenses

interface ExpenseRepository {
    suspend fun create(title: String, quantity: Double) : Result<Unit>

    suspend fun addUsersFromGroupToExpense(groupId: Long, expenseId: Long) : Result<Unit>
}