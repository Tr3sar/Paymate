package dadm.jmartor.paymate.ui.group.ui.deudas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.FragmentDeudasBinding
import dadm.jmartor.paymate.ui.pagardeuda.PagarDeudaActivity
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

        val extras = requireActivity().intent.extras!!
        val groupId : Long = extras.getLong("groupId")
        val groupName : String = extras.getString("groupName").toString()
        val username : String = viewModel.getUserName()

        viewModel.getDebtsList(groupId)

        viewModel.debtList.observe(viewLifecycleOwner) {debtList ->
            adapter.submitList(debtList)
        }

        val payDebtButton: Button = view.findViewById(R.id.payDebtButton)

        viewModel.userDebt.observe(viewLifecycleOwner) {userDebt ->
            if (userDebt == null || userDebt.quantity <= 0) {
                payDebtButton.isEnabled = false
            } else {
                payDebtButton.isEnabled = true
                payDebtButton.setOnClickListener {
                    val intent = Intent(activity, PagarDeudaActivity::class.java)
                    intent.putExtra("username", username)
                    intent.putExtra("groupId", groupId)
                    intent.putExtra("groupName", groupName)
                    intent.putExtra("quantity", userDebt.quantity)
                    startActivity(intent)
                }
            }
        }

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getDebtsList(groupId)
        }

        viewModel.iconoVisible.observe(viewLifecycleOwner) {iconoVisible ->
            binding.swipeToRefresh.isRefreshing = iconoVisible
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}