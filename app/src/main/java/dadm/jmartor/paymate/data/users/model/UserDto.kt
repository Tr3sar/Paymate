package dadm.jmartor.paymate.data.users.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(val name: String, val password: String)
