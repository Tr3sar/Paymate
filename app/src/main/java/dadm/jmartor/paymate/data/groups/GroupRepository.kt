package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.ui.domain.model.User

interface GroupRepository {

    suspend fun create(name: String) : Result<Unit>

    suspend fun getUsers(groupId: Long) : Result<List<User>>
}