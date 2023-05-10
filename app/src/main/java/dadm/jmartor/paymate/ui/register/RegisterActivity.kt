package dadm.jmartor.paymate.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.ActivityRegisterBinding
import dadm.jmartor.paymate.ui.login.LoginActivity
import dadm.jmartor.paymate.ui.login.LoginViewModel
import dadm.jmartor.paymate.utils.NoInternetException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.btnRegister.setOnClickListener() {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNullOrEmpty()) {
                Snackbar.make(binding.root, "El nombre de usuario no puede estar vacío", Snackbar.LENGTH_SHORT).show()
            } else if (password.isNullOrEmpty()) {
                Snackbar.make(binding.root, "La contraseña no puede estar vacía", Snackbar.LENGTH_SHORT).show()
            } else {
                viewModel.register(username, password)
            }
        }

        binding.tvLogin.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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
                            R.string.registerError,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                viewModel.resetError()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}