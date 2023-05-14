package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.users.model.UserDto
import retrofit2.Response

interface GroupDataSource {

    suspend fun create(name: String) : Response<Unit>

    suspend fun getUsers(groupId: Long) : Response<List<UserDto>>

}