package dadm.jmartor.paymate.ui.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.data.users.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var userRepository: UserRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    private val _containsErrors: MutableLiveData<Throwable?> = MutableLiveData<Throwable?>()

    val loginResult: LiveData<Boolean> get() = _loginResult
    val containsErrors: LiveData<Throwable?> get() = _containsErrors

    fun login(username: String, password: String) {
        viewModelScope.launch {
            userRepository.login(username, password).fold(onSuccess = {
                if (it.username == "error" && it.password == "error") {
                    _containsErrors.value = Throwable("Credenciales incorrectos.")
                    _loginResult.value = false
                } else {
                    _loginResult.value = true
                }
            }, onFailure = {
                _containsErrors.value = it
                _loginResult.value = false
            })
        }
    }

    fun resetError() {
        _containsErrors.value = null
    }
}