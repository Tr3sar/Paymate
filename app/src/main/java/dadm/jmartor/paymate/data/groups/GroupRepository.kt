package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.groups.model.ExpenseDto
import dadm.jmartor.paymate.model.Expense

interface GroupRepository {

    suspend fun create(name: String) : Result<Unit>

    suspend fun getExpensesFromGroup(groupId: Long) : Result<List<Expense>>

}