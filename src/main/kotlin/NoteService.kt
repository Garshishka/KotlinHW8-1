object NoteService : CrudService<Note>() {

    override fun create(elem: Note): Note {
        elementId++
        elements += elem.copy(id = elementId)
        return elements.last()
    }

    override fun findById(id: Int): Note? {
        for (element in elements) {
            if (element.id == id) {
                return element
            }
        }
        return null
    }

    override fun edit(elem: Note): Boolean {
        val note = findById(elem.id) ?: throw NoteNotFoundException("no note with ${elem.id}")
        elements[elements.indexOf(note)] = note.copy(title = elem.title, text = elem.text, date = elem.date)
        return true
    }

    override fun delete(id: Int): Boolean {
        val note = findById(id) ?: throw NoteNotFoundException("no note with $id")
        elements.removeAt(elements.indexOf(note))
        return true
    }

    class NoteNotFoundException(message: String) : RuntimeException(message)
}