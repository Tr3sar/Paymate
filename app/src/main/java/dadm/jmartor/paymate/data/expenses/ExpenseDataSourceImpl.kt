package dadm.jmartor.paymate.data.expenses

import android.util.Log
import dadm.jmartor.paymate.data.expenses.model.ExpenseDto
import dadm.jmartor.paymate.data.groups.model.GroupDto
import dadm.jmartor.paymate.model.Expense
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import javax.inject.Inject

class ExpenseDataSourceImpl @Inject() constructor(var retrofit: Retrofit) : ExpenseDataSource {
    private val retrofitExpenseService = retrofit.create(ExpenseRetrofit::class.java)

    interface ExpenseRetrofit {
        @POST("expense")
        suspend fun create(@Body expenseDto: ExpenseDto): Response<Unit>

        @PUT("expense/add_users")
        suspend fun addUsersFromGroupToExpense(
            @Query("groupId") groupId: Long,
            @Query("expenseId") expenseId: Long) : Response<Unit>
    }

    override suspend fun create(title: String, quantity: Double): Response<Unit> =
        try{
            val expense: ExpenseDto = ExpenseDto(title, quantity)
            retrofitExpenseService.create(expense)
        }catch (e:Exception){
            Log.e("ERROR", "Error create ExpenseDataSourceImpl")
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }

    override suspend fun addUsersFromGroupToExpense(groupId: Long, expenseId: Long): Response<Unit> =
        try{
            retrofitExpenseService.addUsersFromGroupToExpense(groupId, expenseId)
        }catch (e:Exception){
            Log.e("ERROR", "Error adding users to expense ExpenseDataSourceImpl")
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }


}