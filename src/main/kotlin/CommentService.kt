object CommentService : CrudService<Comment>() {

    //here we will have main comment list for all comments (to save deleted comments)
    //in addition to specific note comment lists

    override fun create(elem: Comment): Int {
        elementId++
        elements += elem.copy(id = elementId)
        return elementId
    }

    override fun getById(id: Int): Comment? {
        for (element in elements) {
            if (element.id == id) {
                return element
            }
        }
        return null
    }

    override fun delete(id: Int): Boolean {
        val comment = getById(id) ?: throw CommentNotFoundException("no comment with $id")
        elements[elements.indexOf(comment)].deleted = true
        return true
    }

    override fun edit(elem: Comment): Boolean {
        val comment = getById(elem.id) ?: throw CommentNotFoundException("no comment with ${elem.id}")
        elements[elements.indexOf(comment)] = comment.copy(message = elem.message)
        return true
    }

    fun restore(id: Int): Boolean {
        val comment = getById(id) ?: throw CommentNotFoundException("no comment with $id")
        elements[elements.indexOf(comment)].deleted = false
        return true
    }

    class CommentNotFoundException(message: String) : RuntimeException(message)
}