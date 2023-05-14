package dadm.jmartor.paymate.ui.newgasto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.ActivityLoginBinding
import dadm.jmartor.paymate.databinding.ActivityNewExpenseBinding
import dadm.jmartor.paymate.ui.login.LoginViewModel
import dadm.jmartor.paymate.utils.NoInternetException
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
            val title = binding.etExpenseTitle.text.toString()
            val quantity = binding.etExpenseQuantity.text.toString().toDouble()

            if (title.isNullOrEmpty()) {
                Snackbar.make(binding.root, "El título no puede ser vacío", Snackbar.LENGTH_SHORT).show()
            } else if (quantity.isNaN()) {
                Snackbar.make(binding.root, "La cantidad debe ser un número", Snackbar.LENGTH_SHORT).show()
            } else {
                viewModel.createExpense(title, quantity)
            }
        }

        viewModel.createExpenseResult.observe(this) {createExpenseResult ->
            if (createExpenseResult) {
                finish()
            } else {
                Snackbar.make(binding.root, "Ha ocurrido un error inesperado, vuelve a intentarlo más tarde.", Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.containsErrors.observe(this) {error ->
            if (error != null) {
                when (error) {
                    is NoInternetException -> {
                        Snackbar.make(
                            binding.root,
                            R.string.internetException,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        Snackbar.make(
                            binding.root,
                            R.string.create_expense_error,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                viewModel.resetError()
            }
        }
    }
}