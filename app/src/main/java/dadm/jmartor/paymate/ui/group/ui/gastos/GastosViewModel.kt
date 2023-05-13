package dadm.jmartor.paymate.ui.group.ui.gastos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.jmartor.paymate.data.groups.GroupRepository
import dadm.jmartor.paymate.model.Expense
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GastosViewModel @Inject() constructor(
    var groupRepository: GroupRepository
) : ViewModel() {

    private val _expensesList: MutableLiveData<List<Expense>> = MutableLiveData<List<Expense>>()

    val expensesList: LiveData<List<Expense>> get() = _expensesList

    fun getExpensesList() {
        viewModelScope.launch {
            groupRepository.getExpensesFromGroup(5).fold(onSuccess = {
                _expensesList.value = it
            }, onFailure = {

            })
        }
    }
}