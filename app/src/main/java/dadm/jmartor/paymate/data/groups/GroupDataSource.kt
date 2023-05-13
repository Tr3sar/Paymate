package dadm.jmartor.paymate.data.groups

import dadm.jmartor.paymate.data.groups.model.GroupDto
import retrofit2.Response

interface GroupDataSource {

    suspend fun create(name: String) : Response<Unit>

    suspend fun addUser (groupId: Long, name: String) : Response<Unit>

    suspend fun getMyGroups(name: String) : Response<List<GroupDto>>

}