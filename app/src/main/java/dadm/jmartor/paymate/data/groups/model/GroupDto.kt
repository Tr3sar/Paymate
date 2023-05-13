package dadm.jmartor.paymate.data.groups.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupDto (
    val id: Long,
    val name: String
    )
