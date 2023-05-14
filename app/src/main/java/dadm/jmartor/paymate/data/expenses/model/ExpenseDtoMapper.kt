import dadm.jmartor.paymate.data.expenses.model.ExpenseDto
import dadm.jmartor.paymate.model.Expense
import retrofit2.Response
import java.io.IOException

fun ExpenseDto.toDomain() = Expense(id, title, quantity)

fun Response<List<ExpenseDto>>.toDomainList() =
    if (isSuccessful) {
        val expenseList = body()?.map{ it.toDomain() } ?: emptyList()
        Result.success(expenseList)
    }
    else Result.failure(IOException())