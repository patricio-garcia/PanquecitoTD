package cl.serlitoral.pasteleriapanquecitotd.data.model

data class CakeDetail(
    val id: Int,
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
