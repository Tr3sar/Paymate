package dadm.jmartor.paymate.ui.newgasto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dadm.jmartor.paymate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewExpenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_expense)
    }
}