package dadm.jmartor.paymate.ui.newgasto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.ActivityLoginBinding
import dadm.jmartor.paymate.databinding.ActivityNewExpenseBinding
import dadm.jmartor.paymate.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewExpenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewExpenseBinding
    private val viewModel: NewExpenseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewExpenseBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnCrear.setOnClickListener() {
            Snackbar.make(binding.root, "Botón pulsado", Snackbar.LENGTH_SHORT).show()
            viewModel.createExpense("prova", 30.0)
        }

        viewModel.createExpenseResult.observe(this) {createExpenseResult ->
            if (createExpenseResult) {
                finish()
            }
        }
    }
}