package trpl.nim234311040.re_collection

data class Event (
    val title : String,
    val description : String? = null,
    val daypart : Daypart,
    val durationInMinutes : Int,
)