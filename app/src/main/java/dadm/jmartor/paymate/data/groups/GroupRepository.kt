package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.groups.model.GroupDto
import dadm.jmartor.paymate.model.User

import dadm.jmartor.paymate.model.Expense
import dadm.jmartor.paymate.model.Group
import retrofit2.Response

interface GroupRepository {

    suspend fun create(name: String) : Result<Unit>

    suspend fun getUsers(groupId: Long) : Result<List<User>>

    suspend fun getExpensesFromGroup(groupId: Long) : Result<List<Expense>>

    suspend fun addUser (groupId: Long, name: String) : Result<Unit>

    suspend fun getMyGroups(name: String) : Result<List<Group>>

    suspend fun getAllGroups() : Result<List<Group>>
}