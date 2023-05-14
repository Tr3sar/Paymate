package dadm.jmartor.paymate.ui.newgasto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.jmartor.paymate.data.expenses.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewExpenseViewModel @Inject() constructor(
    var expenseRepository: ExpenseRepository
) : ViewModel() {
    private val _createExpenseResult = MutableLiveData<Boolean>()
    private val _containsErrors: MutableLiveData<Throwable?> = MutableLiveData<Throwable?>()

    val createExpenseResult: LiveData<Boolean> get() = _createExpenseResult
    val containsErrors: LiveData<Throwable?> get() = _containsErrors

    fun createExpense(title: String, quantity: Double) {
        viewModelScope.launch {
            expenseRepository.create(title, quantity).fold(onSuccess = {
                expenseRepository.addUsersFromGroupToExpense(1, 6).fold(onSuccess = {
                    _createExpenseResult.value = true
                }, onFailure = {
                    _createExpenseResult.value = false
                    _containsErrors.value = it
                })
            }, onFailure = {
                _createExpenseResult.value = false
                _containsErrors.value = it
            })
        }
    }

    fun resetError() {
        _containsErrors.value = null
    }
}