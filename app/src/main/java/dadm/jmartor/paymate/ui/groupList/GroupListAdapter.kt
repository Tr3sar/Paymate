package dadm.jmartor.paymate.ui.groupList

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.databinding.GroupItemBinding
import dadm.jmartor.paymate.model.Expense
import dadm.jmartor.paymate.model.Group
import dadm.jmartor.paymate.ui.group.GrupoActivity

class GroupListAdapter(private var itemClicked: ItemClicked) : ListAdapter<Group, GroupListAdapter.ViewHolder>(
    GroupDiffSingleton.GroupDiff
) {

    class ViewHolder(private val binding: GroupItemBinding, val itemClicked: ItemClicked) : RecyclerView.ViewHolder(binding.root) {
        init {

            }


        fun bind(Group: Group) {
            binding.root.setOnClickListener{
                itemClicked.onClick(Group)}
            binding.titleGroup.text = Group.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GroupItemBinding.inflate(LayoutInflater.from(parent.context)), itemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

interface ItemClicked {
    fun onClick(Group : Group)
}

class GroupDiffSingleton {
    object GroupDiff: DiffUtil.ItemCallback<Group>() {
        override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem == newItem
        }
    }
}
