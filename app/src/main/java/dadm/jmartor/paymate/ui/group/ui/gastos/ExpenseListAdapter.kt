package dadm.jmartor.paymate.ui.group.ui.gastos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.databinding.ExpenseItemBinding
import dadm.jmartor.paymate.model.Expense

class ExpenseListAdapter(private val itemClicked: ItemClicked) : ListAdapter<Expense, ExpenseListAdapter.ViewHolder>(
    ExpenseDiffSingleton.ExpenseDiff
) {

    class ViewHolder(private val binding: ExpenseItemBinding, itemClicked: ItemClicked) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                Snackbar.make(binding.root, "No ha sido implementado aún", Snackbar.LENGTH_SHORT).show()
            }
        }

        fun bind(expense: Expense) {
            binding.titleGasto.text = expense.title
            binding.quantityGasto.text = expense.quantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ExpenseItemBinding.inflate(LayoutInflater.from(parent.context)), itemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

interface ItemClicked {
    fun onClick()
}

class ExpenseDiffSingleton {
    object ExpenseDiff: DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }
    }
}
