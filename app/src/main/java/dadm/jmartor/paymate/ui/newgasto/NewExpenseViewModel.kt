package dadm.jmartor.paymate.ui.newgasto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewExpenseViewModel @Inject() constructor() : ViewModel() {
    private val _createExpenseResult = MutableLiveData<Boolean>()
    private val _containsErrors: MutableLiveData<Throwable?> = MutableLiveData<Throwable?>()

    val createExpenseResult: LiveData<Boolean> get() = _createExpenseResult
    val containsErrors: LiveData<Throwable?> get() = _containsErrors

    fun createExpense(title: String, quantity: Double) {
        _createExpenseResult.value = true
    }

    fun resetError() {
        _containsErrors.value = null
    }
}