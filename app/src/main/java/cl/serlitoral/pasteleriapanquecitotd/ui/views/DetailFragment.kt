package cl.serlitoral.pasteleriapanquecitotd.ui.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.serlitoral.pasteleriapanquecitotd.R
import cl.serlitoral.pasteleriapanquecitotd.databinding.FragmentDetailBinding
import cl.serlitoral.pasteleriapanquecitotd.ui.viewmodel.CakeVM
import coil.load

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val cakeVM: CakeVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater)
        binding.chkDelivery.isClickable = false

        val cake = cakeVM.getSelected()
        cakeVM.consumeDetail(cake.id)

        cakeVM.getDetail(cake.id).observe(viewLifecycleOwner, {
            it?.let {
                binding.imgCakeDetail.load(it.image)
                binding.tvCakeTitleDetail.text = it.title
                binding.tvCakeShapeDetail.text = it.shape
                binding.tvCakeSizeDetail.text = it.size
                binding.tvCakePriceDetail.text = "$".plus(it.price.toString())
                binding.chkDelivery.isChecked = it.delivery

                if(binding.chkDelivery.isChecked) {
                    binding.chkDelivery.setText("Tiene despacho a domicilio")
                } else {
                    binding.chkDelivery.setText("NO tiene despacho a domicilio")
                }

                binding.tvCakeDescriptionDetail.text = it.detailDescription
            }
        })

        binding.fabEmailSend.setOnClickListener {
            val recipiens = arrayOf("ventas@panquecitochile.cl")
            val subject = "Quisiera encargar el pastel ${cake.title} ID ${cake.id}"
            val message = "Hola\n" +
                    "Quisiera pedir el pastel ${cake.title}  de código ${cake.id} y me gustaría que me contactaran a este\n" +
                    "correo o al siguiente número ___________ (indique número aquí)\n" +
                    "Quedo atento.”\n"

            sendEmail(recipiens, subject, message)
        }

        return binding.root
    }

    fun sendEmail(recipiens: Array<String>, subject: String, message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, recipiens)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        startActivity(intent)

    }
}