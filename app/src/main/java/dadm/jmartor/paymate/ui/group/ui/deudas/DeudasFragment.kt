package dadm.jmartor.paymate.ui.group.ui.deudas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dadm.jmartor.paymate.databinding.FragmentDeudasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeudasFragment : Fragment() {

    private var _binding: FragmentDeudasBinding? = null

    private val binding get() = _binding!!

    private val viewModel: DeudasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDeudasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}