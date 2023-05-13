package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.groups.model.GroupDto
import dadm.jmartor.paymate.model.Group


interface GroupRepository {

    suspend fun create(name: String) : Result<Unit>

    suspend fun addUser (groupId: Long, name: String) : Result<Unit>

    suspend fun getMyGroups(name: String) : Result<List<Group>>

}