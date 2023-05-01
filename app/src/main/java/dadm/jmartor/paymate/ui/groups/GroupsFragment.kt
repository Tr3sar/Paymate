package dadm.jmartor.paymate.ui.groups

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.FragmentGroupsBinding

class GroupsFragment : Fragment(R.layout.fragment_groups) {
    private var _binding : FragmentGroupsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGroupsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}