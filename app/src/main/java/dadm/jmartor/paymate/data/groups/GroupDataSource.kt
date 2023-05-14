package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.users.model.UserDto
import dadm.jmartor.paymate.data.expenses.model.ExpenseDto
import dadm.jmartor.paymate.data.groups.model.GroupDto
import retrofit2.Response

interface GroupDataSource {

    suspend fun create(name: String) : Response<Unit>

    suspend fun getUsers(groupId: Long) : Response<List<UserDto>>

    suspend fun getExpensesFromGroup(groupId: Long) : Response<List<ExpenseDto>>

    suspend fun addUser (groupId: Long, name: String) : Response<Unit>

    suspend fun getMyGroups(name: String) : Response<List<GroupDto>>
}