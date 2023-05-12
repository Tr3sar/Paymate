package dadm.jmartor.paymate.ui.newgroup

import android.annotation.SuppressLint
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
    private var listMembers: List<String> = emptyList()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewGroupBinding.bind(view)

        binding.addMember.setOnClickListener {
            var members: String = binding.members.text.toString()
            var member: String = binding.personName.text.toString()
            binding.members.text = members + member + "\n"
            listMembers.plus(member)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}