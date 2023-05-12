package dadm.jmartor.paymate.ui.group.ui.gastos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dadm.jmartor.paymate.databinding.FragmentGastosBinding

class GastosFragment : Fragment() {

    private var _binding: FragmentGastosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val gastosViewModel =
            ViewModelProvider(this).get(GastosViewModel::class.java)

        _binding = FragmentGastosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}