package dadm.jmartor.paymate.data.expenses.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExpenseDto(val id: Long, val title: String, val quantity: Double) {
    constructor(title: String, quantity: Double) : this(0, title, quantity)
}