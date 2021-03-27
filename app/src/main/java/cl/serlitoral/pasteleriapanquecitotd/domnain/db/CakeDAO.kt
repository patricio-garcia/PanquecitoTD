package cl.serlitoral.pasteleriapanquecitotd.domnain.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CakeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCakes(cakes: List<CakeEntity>)

    @Query("SELECT * FROM cakes")
    fun getCakes(): LiveData<List<CakeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: CakeDetailEntity)

    @Query("SELECT * FROM cake_detail WHERE id=:pid")
    fun getDetail(pid: Int): LiveData<CakeDetailEntity>
}