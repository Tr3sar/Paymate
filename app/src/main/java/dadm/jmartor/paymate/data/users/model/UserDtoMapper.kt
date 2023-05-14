package dadm.jmartor.paymate.data.users.model

import dadm.jmartor.paymate.model.User
import retrofit2.Response
import java.io.IOException

fun UserDto.toDomain() = User(name, password)

fun Response<UserDto>.toDomain() =
    if (isSuccessful) Result.success((body() as UserDto).toDomain())
    else Result.failure(IOException())

fun Response<List<UserDto>>.toDomainList() =
    if (isSuccessful) {
        val userDto = body()?.map{ it.toDomain() } ?: emptyList()
        Result.success(userDto)
    }
    else Result.failure(IOException())

fun Response<Unit>.toUnitDomain() =
    if (isSuccessful) Result.success(Unit)
    else Result.failure(IOException())

fun Response<Double>.toDoubleDomain() =
    if (isSuccessful) Result.success((body() as Double).toDouble())
    else Result.failure(IOException())