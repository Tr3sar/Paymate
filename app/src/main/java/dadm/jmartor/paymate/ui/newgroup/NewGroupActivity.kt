package dadm.jmartor.paymate.ui.newgroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.ActivityNewGroupBinding
import dadm.jmartor.paymate.model.User
import dadm.jmartor.paymate.utils.NoInternetException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewGroupBinding
    private val viewModel: NewGroupViewModel by viewModels()
    private var listMembers: List<User> = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_new_group)
        binding = ActivityNewGroupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.members.text = viewModel.members.value

        binding.addMember.setOnClickListener {
            var member: String = binding.personName.text.toString()
            var isUser: Boolean = false
            listMembers = viewModel.getAllUsers()

            for (user: User in listMembers){
                Log.e("EEEEEEEEEEEEEEEEEEEEEEEE", "yeeee")
                if (user.username==member){
                    isUser = true
                }
            }

            if(isUser){
                viewModel.setMembers(member + "\n")
                binding.members.text = viewModel.members.value
                viewModel.addMembersToList(member)
                binding.personName.text.clear()
            }
        }

        binding.createGroup.setOnClickListener {
            viewModel.create(binding.groupName.text.toString(), viewModel.listMembers.value!!)
            viewModel.resetMembersList()
            binding.members.text = ""
            viewModel.resetMembers()
            binding.groupName.text.clear()
        }

        viewModel.containsErrors.observe(this) {error ->
            if (error != null) {
                when (error) {
                    is NoInternetException -> {
                        Snackbar.make(
                            binding.root,
                            R.string.internetException,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        Snackbar.make(
                            binding.root,
                            R.string.create_expense_error,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                viewModel.resetError()
            }
        }

        viewModel.createGroupResult.observe(this) {createGroupResult ->
            if (createGroupResult) {
                finish()
            } else {
                Snackbar.make(binding.root, "Ha ocurrido un error inesperado, vuelve a intentarlo más tarde.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}