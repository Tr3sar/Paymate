package dadm.jmartor.paymate.ui.newgroup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.FragmentNewGroupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewGroupFragment : Fragment(R.layout.fragment_new_group) {
    private var _binding : FragmentNewGroupBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewGroupViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewGroupBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}