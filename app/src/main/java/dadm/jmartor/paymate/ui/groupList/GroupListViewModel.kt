package dadm.jmartor.paymate.ui.groupList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.jmartor.paymate.PaymateApplication
import dadm.jmartor.paymate.data.groups.GroupRepository
import dadm.jmartor.paymate.model.Group
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class GroupListViewModel @Inject() constructor(
    @ApplicationContext private val application : Context,
    private val groupRepository: GroupRepository
): ViewModel() {

    private val group_List: MutableLiveData<List<Group>> = MutableLiveData<List<Group>>()
    private val _iconoVisible: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    val iconoVisible: LiveData<Boolean> get() = _iconoVisible
    val groupList: LiveData<List<Group>> get() = group_List


fun getGroupList() {
    _iconoVisible.value = true

    val paymateApplication = application as PaymateApplication
    val name = paymateApplication.username

    viewModelScope.launch {
        val name = paymateApplication.username.toString()
        groupRepository.getMyGroups(name).fold(onSuccess = {
            group_List.value = it
        }, onFailure = {

        })
    }

    _iconoVisible.value = false
}

}

