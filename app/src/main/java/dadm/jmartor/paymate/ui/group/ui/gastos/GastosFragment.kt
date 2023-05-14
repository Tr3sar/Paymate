package dadm.jmartor.paymate.ui.group.ui.gastos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.FragmentGastosBinding
import dadm.jmartor.paymate.ui.newgasto.NewExpenseActivity
import dadm.jmartor.paymate.ui.newgroup.NewGroupActivity
import dadm.jmartor.paymate.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GastosFragment : Fragment(R.layout.fragment_gastos) {

    private var _binding: FragmentGastosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GastosViewModel by activityViewModels()

    private val itemClicked = object : ItemClicked {
        override fun onClick() {
            Snackbar.make(binding.root, "No ha sido implementado aún", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGastosBinding.bind(view)

        val adapter = ExpenseListAdapter(itemClicked)
        binding.recyclerViewGastos.adapter = adapter

        getExpensesList()

        viewModel.expensesList.observe(viewLifecycleOwner) {expenseList ->
            adapter.submitList(expenseList)
        }

        //newExpense
        binding.btnAdd.setOnClickListener() {
            val intent = Intent(activity, NewExpenseActivity::class.java)
            startActivity(intent)
        }

        //refresh
        binding.swipeToRefresh.setOnRefreshListener() {
            getExpensesList()
        }

        viewModel.iconoVisible.observe(viewLifecycleOwner) { iconoVisible ->
            binding.swipeToRefresh.isRefreshing = iconoVisible
        }
    }

    private fun getExpensesList() {
        viewModel.getExpensesList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}