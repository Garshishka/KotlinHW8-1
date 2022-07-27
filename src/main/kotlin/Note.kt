data class Note(

    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val id: Int = 0,
    var comments: Int = 0,

    var commentsList: MutableList<Comment> = mutableListOf<Comment>()
)