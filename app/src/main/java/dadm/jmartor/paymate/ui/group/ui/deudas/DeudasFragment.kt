package dadm.jmartor.paymate.ui.group.ui.deudas

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.FragmentDeudasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeudasFragment : Fragment(R.layout.fragment_deudas) {

    private var _binding: FragmentDeudasBinding? = null

    private val binding get() = _binding!!

    private val viewModel: DeudasViewModel by activityViewModels()

    private val itemClicked = object : ItemClicked {
        override fun onClick() {
            Snackbar.make(binding.root, "No ha sido implementado aún", Snackbar.LENGTH_SHORT).show()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDeudasBinding.bind(view)

        val adapter = DebtListAdapter(itemClicked)
        binding.recyclerViewDeudas.adapter = adapter

        viewModel.getDebtsList()

        viewModel.debtList.observe(viewLifecycleOwner) {debtList ->
            adapter.submitList(debtList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}