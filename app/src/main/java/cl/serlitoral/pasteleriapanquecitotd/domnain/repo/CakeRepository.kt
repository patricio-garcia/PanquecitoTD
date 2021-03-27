package cl.serlitoral.pasteleriapanquecitotd.domnain.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cl.serlitoral.pasteleriapanquecitotd.data.model.Cake
import cl.serlitoral.pasteleriapanquecitotd.data.model.CakeDetail
import cl.serlitoral.pasteleriapanquecitotd.data.remote.RetrofitClient
import cl.serlitoral.pasteleriapanquecitotd.domnain.db.CakeApplication
import cl.serlitoral.pasteleriapanquecitotd.domnain.db.CakeDetailEntity
import cl.serlitoral.pasteleriapanquecitotd.domnain.db.CakeEntity

class CakeRepository {

    private val cakeDB = CakeApplication.db!!.cakeDao()

    fun cakeList(): LiveData<List<Cake>> = Transformations.map(cakeDB.getCakes()) {
      it.map {
          db2api(it)
      }
    }

    fun getDetailFromDB(pid: Int): LiveData<CakeDetail> = Transformations.map(cakeDB.getDetail(pid)) {
        it?.let {
            db2api(it)
        }
    }

    suspend fun getCakes() {
        val response = RetrofitClient
            .retrofitInstance()
            .getCakes()

        response.let {
            when(it.isSuccessful) {
                true -> {
                    response.body()?.let { cakeList ->
                        val map = cakeList.map { cake ->
                            api2db(cake)
                        }
                        cakeDB.insertCakes(map)
                    }
                }
                false -> {
                    Log.d("Repo", "error en Repo ")
                }
            }
        }
    }

    suspend fun getDetail(id: Int) {
        val response = RetrofitClient.retrofitInstance().getCake(id.toString())

        Log.d("Repo", "$id")

        if(response.isSuccessful) {
            response.body()?.let { detail ->
                cakeDB.insertDetail(api2db(detail))
            }
        } else {
            Log.d("Repo", "error en el Detalle del Repo ${response.code()} ")
        }
    }
}

fun api2db(cake: Cake): CakeEntity {
    return CakeEntity(cake.id, cake.title, cake.previewDescription, cake.size, cake.price, cake.image)
}

fun db2api(cakeEntity: CakeEntity): Cake {
    return Cake(cakeEntity.id, cakeEntity.title, cakeEntity.previewDescription, cakeEntity.size, cakeEntity.price, cakeEntity.image)
}

fun api2db(detail: CakeDetail): CakeDetailEntity {
    return CakeDetailEntity(detail.id, detail.title, detail.previewDescription, detail.detailDescription, detail.image, detail.shape, detail.size, detail.price, detail.lastPrice, detail.delivery)
}

fun db2api(detailEntity: CakeDetailEntity): CakeDetail {
    return CakeDetail(detailEntity.id, detailEntity.title, detailEntity.previewDescription, detailEntity.detailDescription, detailEntity.image, detailEntity.shape, detailEntity.size, detailEntity.price, detailEntity.lastPrice, detailEntity.delivery)
}