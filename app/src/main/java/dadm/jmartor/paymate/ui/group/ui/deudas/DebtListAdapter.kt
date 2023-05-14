package dadm.jmartor.paymate.ui.group.ui.deudas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.databinding.DebtItemBinding
import dadm.jmartor.paymate.model.Debt

class DebtListAdapter(private val itemClicked: ItemClicked) : ListAdapter<Debt, DebtListAdapter.ViewHolder>(
    DebtDiffSingleton.DebtDiff
) {

    class ViewHolder(private val binding: DebtItemBinding, itemClicked: ItemClicked) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                Snackbar.make(binding.root, "No ha sido implementado aún", Snackbar.LENGTH_SHORT).show()
            }
        }

        fun bind(debt: Debt) {
            binding.username.text = debt.username
            binding.quantity.text = debt.quantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DebtItemBinding.inflate(LayoutInflater.from(parent.context)), itemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

interface ItemClicked {
    fun onClick()
}

class DebtDiffSingleton {
    object DebtDiff: DiffUtil.ItemCallback<Debt>() {
        override fun areItemsTheSame(oldItem: Debt, newItem: Debt): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Debt, newItem: Debt): Boolean {
            return oldItem == newItem
        }
    }
}
