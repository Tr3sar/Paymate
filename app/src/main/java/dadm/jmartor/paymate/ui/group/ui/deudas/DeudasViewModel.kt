package dadm.jmartor.paymate.ui.group.ui.deudas

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dadm.jmartor.paymate.PaymateApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class DeudasViewModel @Inject constructor(
    @ApplicationContext private val application: Context,
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        val paymateApplication = application as PaymateApplication
        value = paymateApplication.username
    }
    val text: LiveData<String> = _text
}