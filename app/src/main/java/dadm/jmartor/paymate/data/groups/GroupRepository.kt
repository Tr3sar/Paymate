package dadm.jmartor.paymate.data.groups

interface GroupRepository {

    suspend fun create(name: String) : Result<Unit>

}