package dadm.jmartor.paymate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PaymateApplication : Application(){
    var username: String? = null
}