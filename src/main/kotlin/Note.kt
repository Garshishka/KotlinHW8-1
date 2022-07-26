data class Note(

    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val id: Int = 0,
    val comments: Int = 0,

    val commentsList: MutableList<Comment> = mutableListOf()
)