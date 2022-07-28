object NoteService : CrudService<Note>() {

    override fun create(elem: Note): Int {
        elementId++
        elements += elem.copy(id = elementId)
        return elementId
    }

    override fun getById(id: Int): Note? {
        for (element in elements) {
            if (element.id == id) {
                return element
            }
        }
        return null
    }

    override fun edit(elem: Note): Boolean {
        val note = getById(elem.id) ?: throw NoteNotFoundException("no note with ${elem.id}")
        elements[elements.indexOf(note)] = note.copy(title = elem.title, text = elem.text, date = elem.date)
        return true
    }

    override fun delete(id: Int): Boolean {
        val note = getById(id) ?: throw NoteNotFoundException("no note with $id")
        //marking comments for this note in all-comments list as deleted
        for (comment in note.commentsList){
            CommentService.delete(comment.id)
        }
        elements.removeAt(elements.indexOf(note))
        return true
    }

    //comment system
    fun createComment(comment: Comment): Int {
        val addedCommentNumber = CommentService.create(comment)
        val note = getById(comment.noteId) ?: throw NoteNotFoundException("no note with ${comment.noteId}")
        note.comments++
        val addedComment = CommentService.getById(addedCommentNumber)
        if (addedComment != null) {
            note.commentsList.add(addedComment)
        }  //adding a new comment to all-comments list and then adding the same comment to specific note comments
        return addedCommentNumber
    }

    fun deleteComment(cId: Int): Boolean {
        val comment = CommentService.getById(cId)
        if (comment != null) {
            val note = getById(comment.noteId) ?: throw NoteNotFoundException("no note with ${comment.noteId}")
            note.comments--
            note.commentsList.remove(comment)
        } //in the all-comments list we mark comment as deleted and then remove it from specific note comment list
        CommentService.delete(cId)
        return true
    }

    fun editComment(comment: Comment): Boolean {
        //we take the comment before the edit and use it to find its index in the specific note comments list
        val oldComment = CommentService.getById(comment.id)
        val note = getById(comment.noteId) ?: throw NoteNotFoundException("no note with ${comment.noteId}")
        note.commentsList[note.commentsList.indexOf(oldComment)] =
            oldComment!!.copy(ownerId = comment.ownerId, message = comment.message)
        CommentService.edit(comment)
        return true
    }

    fun getComments(nId: Int): MutableList<Comment> {
        val note = getById(nId) ?: throw NoteNotFoundException("no note with ${nId}")
        return note.commentsList
    }

    fun restoreComment(cId: Int): Boolean {
        val comment = CommentService.getById(cId)
        val note = getById(comment!!.noteId) ?: throw NoteNotFoundException("no note with ${comment.noteId}")
        CommentService.restore(cId)
        note.comments++
        note.commentsList.add(comment)
        return true
    }

    class NoteNotFoundException(message: String) : RuntimeException(message)
}