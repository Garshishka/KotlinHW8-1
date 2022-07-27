data class Comment(

    val noteId: Int,
    val ownerId: Int,
    val message: String,
    val id: Int = 0,
    var deleted: Boolean = false,
    val replyTo: Int? = null
)
