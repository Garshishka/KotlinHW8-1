data class Comment(
    val id: Int,
    val noteId: Int,
    val ownerId: Int,
    val message: String,
    val deleted: Boolean = false,
    val replyTo: Int? = null
)
