package dadm.jmartor.paymate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dadm.jmartor.paymate.R
import dadm.jmartor.paymate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}