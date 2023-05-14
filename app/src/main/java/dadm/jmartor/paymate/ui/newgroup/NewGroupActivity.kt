package dadm.jmartor.paymate.ui.newgroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dadm.jmartor.paymate.databinding.ActivityNewGroupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewGroupBinding
    private val viewModel: NewGroupViewModel by viewModels()
    private var listMembers: List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewGroupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.addMember.setOnClickListener {
            var members: String = binding.members.text.toString()
            var member: String = binding.personName.text.toString()
            binding.members.text = members + member + "\n"
            listMembers.plus(member)
        }

        binding.createGroup.setOnClickListener {
            viewModel.create(binding.groupName.text.toString(), listMembers)
            listMembers = emptyList()
        }
    }
}