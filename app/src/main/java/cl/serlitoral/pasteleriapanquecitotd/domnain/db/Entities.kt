package cl.serlitoral.pasteleriapanquecitotd.domnain.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cakes")
data class CakeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val previewDescription: String,
    val size: String,
    val price: Long,
    val image: String
)

@Entity(tableName = "cake_detail")
data class CakeDetailEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val previewDescription: String,
    val detailDescription: String,
    val image: String,
    val shape: String,
    val size: String,
    val price: Long,
    val lastPrice: Long,
    val delivery: Boolean
)