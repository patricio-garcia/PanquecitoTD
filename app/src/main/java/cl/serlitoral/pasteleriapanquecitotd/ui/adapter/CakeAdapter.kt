package cl.serlitoral.pasteleriapanquecitotd.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.serlitoral.pasteleriapanquecitotd.data.model.Cake
import cl.serlitoral.pasteleriapanquecitotd.databinding.CakeItemBinding
import coil.load

class CakeAdapter: RecyclerView.Adapter<CakeVH>() {

    private var cakeList = listOf<Cake>()
    private val selectedItem = MutableLiveData<Cake>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeVH {
        val binding = CakeItemBinding.inflate(LayoutInflater.from(parent.context))
        return CakeVH(binding)
    }

    override fun onBindViewHolder(holder: CakeVH, position: Int) {
        val cake = cakeList[position]
        holder.bind(cake)
        holder.itemView.setOnClickListener {
            selectedItem.value = cake
        }
    }

    override fun getItemCount() = cakeList.size

    fun selectedItem(): LiveData<Cake> = selectedItem

    fun update(mCakeList: List<Cake>) {
        cakeList = mCakeList
        notifyDataSetChanged()
    }
}

class CakeVH(val binding: CakeItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(cake: Cake) {
        binding.imgCake.load(cake.image)
        binding.tvCakeTitle.text = cake.title
        binding.tvCakeSize.text = cake.size
        binding.tvCakePrice.text = "$".plus(cake.price.toString())
    }
}