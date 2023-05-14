package dadm.jmartor.paymate.ui.groupList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.databinding.ActivityGroupListBinding
import android.content.Intent
import dadm.jmartor.paymate.model.Group
import dadm.jmartor.paymate.ui.group.GrupoActivity
import dadm.jmartor.paymate.ui.newgroup.NewGroupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class GroupListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupListBinding
    private val viewModel: GroupListViewModel by viewModels()

    private val itemClicked = object : ItemClicked {

        override fun onClick(Group: Group) {
            val intent = Intent(this@GroupListActivity, GrupoActivity::class.java)
            intent.putExtra("grupoId", Group.id)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGroupListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = GroupListAdapter(itemClicked)
        binding.recyclerViewGroupList.adapter = adapter

        viewModel.getGroupList()

        viewModel.groupList.observe(this) { groupList ->
            adapter.submitList(groupList)
        }

        binding.addGroup.setOnClickListener {
            val intent = Intent(this, NewGroupActivity::class.java)
            startActivity(intent)
        }


    }
}
