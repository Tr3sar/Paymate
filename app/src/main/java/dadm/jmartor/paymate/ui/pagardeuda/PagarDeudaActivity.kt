package dadm.jmartor.paymate.ui.pagardeuda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dadm.jmartor.paymate.databinding.ActivityPagarDeudaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagarDeudaActivity : AppCompatActivity(){
    private lateinit var binding: ActivityPagarDeudaBinding

    private val viewModel: PagarDeudaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPagarDeudaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val extras = intent.extras!!
        val username : String = extras.getString("username").toString()
        val groupId : Long = extras.getLong("groupId")
        val groupName : String = extras.getString("groupName").toString()
        val quantity : Double = extras.getDouble("quantity")

        val groupNameTextView: TextView = binding.groupNameTextView
        groupNameTextView.text = groupNameTextView.text.toString().plus(groupName)

        val amountToPayTextView: TextView = binding.amountToPayTextView
        amountToPayTextView.text = amountToPayTextView.text.toString().plus(quantity.toString())

        val payAmountEditText: EditText = binding.payAmountEditText

        val confirmPaymentButton: Button = binding.confirmPaymentButton
        confirmPaymentButton.setOnClickListener {
            val payAmount = payAmountEditText.text.toString().toDouble()
            if (payAmount > quantity) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Solo se cobrará la cantidad de $quantity. ¿Desea pagar?")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar") { dialog, id ->
                        viewModel.payDebt(username, groupId, quantity)
                        finish()
                    }
                    .setNegativeButton("Cancelar") { dialog, id ->
                        dialog.cancel()
                    }
                val alert = builder.create()
                alert.show()
            } else {
                viewModel.payDebt(username, groupId, payAmount)
                finish()
            }
        }
    }
}