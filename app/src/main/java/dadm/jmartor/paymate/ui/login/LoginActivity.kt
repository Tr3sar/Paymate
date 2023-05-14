package dadm.jmartor.paymate.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.ActivityLoginBinding
import dadm.jmartor.paymate.ui.group.GrupoActivity
import dadm.jmartor.paymate.ui.groupList.GroupListActivity
import dadm.jmartor.paymate.ui.register.RegisterActivity
import dadm.jmartor.paymate.utils.NoInternetException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener() {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNullOrEmpty()) {
                Snackbar.make(binding.root, "El nombre de usuario no puede estar vacío", Snackbar.LENGTH_SHORT).show()
            } else if (password.isNullOrEmpty()) {
                Snackbar.make(binding.root, "La contraseña no puede estar vacía", Snackbar.LENGTH_SHORT).show()
            } else {
                viewModel.login(username, password)
            }
        }

        binding.tvRegister.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        viewModel.loginResult.observe(this) { loginResult ->
            if (loginResult) {
                val intent = Intent(this, GroupListActivity::class.java)
                startActivity(intent)

                Snackbar.make(binding.root, "Login correcto", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(binding.root, "El usuario y/o contraseña son incorrectos", Snackbar.LENGTH_SHORT).show()
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
                            R.string.loginError,
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