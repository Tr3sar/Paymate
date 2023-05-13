package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.groups.model.ExpenseDto
import retrofit2.Response

interface GroupDataSource {

    suspend fun create(name: String) : Response<Unit>

    suspend fun getExpensesFromGroup(groupId: Long) : Response<List<ExpenseDto>>

}