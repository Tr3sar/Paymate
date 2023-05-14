package dadm.jmartor.paymate.ui.newgroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.ActivityNewGroupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewGroupBinding
    private val viewModel: NewGroupViewModel by viewModels()
    private var listMembers: List<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_new_group)
        binding = ActivityNewGroupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.members.text = viewModel.members.value

        binding.addMember.setOnClickListener {
            var member: String = binding.personName.text.toString()
            viewModel.setMembers(member + "\n")
            binding.members.text = viewModel.members.value
            viewModel.addMembersToList(member)
            binding.personName.text.clear()
        }

        binding.createGroup.setOnClickListener {
            viewModel.create(binding.groupName.text.toString(), viewModel.listMembers.value!!)
            viewModel.resetMembersList()
            binding.members.text = ""
            viewModel.resetMembers()
            binding.groupName.text.clear()
        }
    }
}