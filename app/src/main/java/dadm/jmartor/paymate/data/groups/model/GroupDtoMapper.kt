package dadm.jmartor.paymate.data.groups.model

import dadm.jmartor.paymate.model.Group
import retrofit2.Response
import java.io.IOException

fun GroupDto.toDomain() = Group(id, name)

fun Response<GroupDto>.toDomain() =
    if (isSuccessful) Result.success((body() as GroupDto).toDomain())
    else Result.failure(IOException())

fun Response<Unit>.toUnitDomain() =
    if (isSuccessful) Result.success(Unit)
    else Result.failure(IOException())