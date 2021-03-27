package cl.serlitoral.pasteleriapanquecitotd.data.model

data class Cake(
    val id: Int,
    val title: String,
    val previewDescription: String,
    val size: String,
    val price: Long,
    val image: String
)
