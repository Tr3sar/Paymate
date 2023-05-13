package dadm.jmartor.paymate.ui.newgroup

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
class NewGroupViewModel @Inject constructor(@ApplicationContext private val application: Context, val groupRepository: GroupRepository):ViewModel(){

    private val _members: MutableLiveData<String> = MutableLiveData<String>()
    val members : LiveData<String>
        get() {
            return _members
        }
    private val paymateApplication = application as PaymateApplication

    fun create(name: String, listUsers: List<String>){
        viewModelScope.launch{
            groupRepository.create(name)
            var groupId: Long = -1
            val userName: String = paymateApplication.username!!
            val result = groupRepository.getMyGroups(userName)
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
        }
    }
}