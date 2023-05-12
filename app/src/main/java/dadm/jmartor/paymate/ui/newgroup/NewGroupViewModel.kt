package dadm.jmartor.paymate.ui.newgroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewGroupViewModel @Inject constructor():ViewModel(){

    private val _members: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val members : LiveData<Boolean>
        get() {
            return _members
        }

    fun addMember(name:String){

    }
}