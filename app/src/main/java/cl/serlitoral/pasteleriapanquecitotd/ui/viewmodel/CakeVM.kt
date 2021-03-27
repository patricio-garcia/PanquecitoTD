package cl.serlitoral.pasteleriapanquecitotd.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.serlitoral.pasteleriapanquecitotd.data.model.Cake
import cl.serlitoral.pasteleriapanquecitotd.data.model.CakeDetail
import cl.serlitoral.pasteleriapanquecitotd.domnain.repo.CakeRepository
import kotlinx.coroutines.launch

class CakeVM: ViewModel() {

    private val repo = CakeRepository()

    fun cakeList(): LiveData<List<Cake>> = repo.cakeList()

    fun getCakes() {
        viewModelScope.launch {
            repo.getCakes()
        }
    }

    fun getDetail(id: Int): LiveData<CakeDetail> = repo.getDetailFromDB(id)

    fun consumeDetail(id: Int) {
        viewModelScope.launch {
            repo.getDetail(id)
        }
    }

    private lateinit var selected: Cake

    fun setSelected(cake: Cake) {
        selected = cake
    }

    fun getSelected() = selected
}