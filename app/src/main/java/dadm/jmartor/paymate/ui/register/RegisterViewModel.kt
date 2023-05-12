package dadm.jmartor.paymate.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.jmartor.paymate.data.users.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(var userRepository: UserRepository) : ViewModel() {
    private val _registerResult = MutableLiveData<Boolean>()
    private val _containsErrors : MutableLiveData<Throwable?> = MutableLiveData<Throwable?>()

    val registerResult: LiveData<Boolean> get() = _registerResult
    val containsErrors: LiveData<Throwable?> get() = _containsErrors

    fun register(username: String, password: String) {
        viewModelScope.launch {
            userRepository.register(username, password).fold(onSuccess = {
                _registerResult.value = it != null
            }, onFailure = {
                _containsErrors.value = it
                _registerResult.value = false
            })
        }
    }

    fun resetError() {
        _containsErrors.value = null
    }
}