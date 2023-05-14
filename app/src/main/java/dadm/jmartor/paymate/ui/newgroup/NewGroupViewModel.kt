package dadm.jmartor.paymate.ui.newgroup

import android.content.Context
import android.util.Log
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
class NewGroupViewModel @Inject constructor(@ApplicationContext private val application: Context, val groupRepository: GroupRepository):ViewModel(){

    private val _members: MutableLiveData<String> = MutableLiveData<String>()
    val members : LiveData<String>
        get() {
            return _members
        }
    fun setMembers(data: String) {
        if (_members.value == null){
            _members.value = ""
        }
        _members.value += data
    }

    fun resetMembers(){
        _members.value = ""
    }

    private val _listMembers: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    val listMembers : LiveData<List<String>>
        get() {
            return _listMembers
        }

    fun addMembersToList(member: String){
        if(_listMembers.value == null){
            _listMembers.value = ArrayList<String>()
        }
        _listMembers.value = _listMembers.value!! + member
    }

    fun resetMembersList(){
        _listMembers.value = emptyList()
    }

    private val paymateApplication = application as PaymateApplication

    private val _createGroupResult = MutableLiveData<Boolean>()
    private val _containsErrors: MutableLiveData<Throwable?> = MutableLiveData<Throwable?>()

    val createGroupResult: LiveData<Boolean> get() = _createGroupResult
    val containsErrors: LiveData<Throwable?> get() = _containsErrors

    fun create(name: String, listUsers: List<String>){
        viewModelScope.launch{
            groupRepository.create(name).fold(onSuccess = {
                var groupId: Long = -1
                val result = groupRepository.getAllGroups()
                var listGroups: List<Group> = result.getOrNull() ?: emptyList()
                for (group: Group in listGroups){
                    if (group.name==name){
                        groupId = group.id
                    }
                }
                for (user: String in listUsers){
                    groupRepository.addUser(groupId, user)
                }
                groupId = -1
                _createGroupResult.value = true
            }, onFailure = {
                _createGroupResult.value = false
                _containsErrors.value = it
            })
        }
    }

    fun resetError() {
        _containsErrors.value = null
    }
}