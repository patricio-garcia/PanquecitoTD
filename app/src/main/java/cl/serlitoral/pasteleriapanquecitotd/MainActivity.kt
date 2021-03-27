package cl.serlitoral.pasteleriapanquecitotd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import cl.serlitoral.pasteleriapanquecitotd.databinding.ActivityMainBinding
import cl.serlitoral.pasteleriapanquecitotd.ui.viewmodel.CakeVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val cakeVM: CakeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cakeVM.getCakes()
    }
}