package dadm.jmartor.paymate.ui.newgroup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.FragmentNewGroupBinding

class NewGroupFragment : Fragment(R.layout.fragment_new_group) {
    private var _binding : FragmentNewGroupBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentNewGroupBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}