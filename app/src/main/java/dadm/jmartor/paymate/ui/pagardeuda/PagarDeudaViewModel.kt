package dadm.jmartor.paymate.ui.pagardeuda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.jmartor.paymate.data.users.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagarDeudaViewModel @Inject constructor(
    var userRepository: UserRepository
) : ViewModel() {

    fun payDebt(username: String, groupId: Long, quantity: Double) {
        viewModelScope.launch {
            userRepository.payDebt(username, groupId, quantity)
        }
    }

}