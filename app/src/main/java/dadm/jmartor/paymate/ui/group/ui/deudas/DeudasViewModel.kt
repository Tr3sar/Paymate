package dadm.jmartor.paymate.ui.group.ui.deudas

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.jmartor.paymate.data.groups.GroupRepository
import dadm.jmartor.paymate.data.users.UserRepository
import dadm.jmartor.paymate.model.Debt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeudasViewModel @Inject constructor(
    var groupRepository: GroupRepository,
    var userRepository: UserRepository
) : ViewModel() {

    private val _debtsList : MutableLiveData<List<Debt>> = MutableLiveData<List<Debt>>()

    val debtList : LiveData<List<Debt>> get() = _debtsList

    private val _userDebt : MutableLiveData<Debt> = MutableLiveData<Debt>()

    val userDebt : LiveData<Debt> get() = _userDebt

    fun getDebtsList(groupId : Long, username : String) {
        val newList = mutableListOf<Debt>()
        viewModelScope.launch {
            groupRepository.getUsers(groupId).fold(onSuccess = { users ->
                for (user in users) {
                    userRepository.getDebt(user.username, groupId).fold(onSuccess = {quantity ->
                        val debt : Debt = Debt(user.username, quantity)
                        newList.add(debt)

                        if (user.username == username) {
                            _userDebt.value = debt
                        }
                    }, onFailure = {

                    })
                }
                _debtsList.postValue(newList)
            }, onFailure = {

            })
        }
    }
}