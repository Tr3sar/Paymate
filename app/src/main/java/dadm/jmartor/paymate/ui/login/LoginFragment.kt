package dadm.jmartor.paymate.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLoginBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}