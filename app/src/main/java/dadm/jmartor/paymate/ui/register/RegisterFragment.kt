package dadm.jmartor.paymate.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRegisterBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}