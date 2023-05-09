package dadm.jmartor.paymate.data.users.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(val username: String, val password: String)
