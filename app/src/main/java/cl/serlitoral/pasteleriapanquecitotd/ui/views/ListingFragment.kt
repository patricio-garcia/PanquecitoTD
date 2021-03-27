package cl.serlitoral.pasteleriapanquecitotd.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import cl.serlitoral.pasteleriapanquecitotd.R
import cl.serlitoral.pasteleriapanquecitotd.databinding.FragmentListingBinding
import cl.serlitoral.pasteleriapanquecitotd.ui.adapter.CakeAdapter
import cl.serlitoral.pasteleriapanquecitotd.ui.viewmodel.CakeVM

class ListingFragment : Fragment() {

    private lateinit var binding: FragmentListingBinding
    private val cakeVM: CakeVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListingBinding.inflate(inflater)

        val adapter = CakeAdapter()
        binding.rvCakeList.adapter = adapter
        binding.rvCakeList.layoutManager = GridLayoutManager(context, 2)
        //binding.rvCakeList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        cakeVM.cakeList().observe(viewLifecycleOwner, {
            adapter.update(it)
        })

        adapter.selectedItem().observe(viewLifecycleOwner, {
            cakeVM.setSelected(it)

            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, DetailFragment())
                ?.addToBackStack("detail")
                ?.commit()
        })

        return binding.root
    }

}