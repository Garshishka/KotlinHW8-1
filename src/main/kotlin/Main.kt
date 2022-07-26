fun main() {
    val note1 = Note(
        4213,
        "My first note",
        "This is my first note",
        2535
    )

    val note2 = Note(
        9034,
        "Another note",
        "This is just another note here",
        2934
    )

    val updatedNote1 = Note(
        4213,
        "I updated this note",
        "It a whole new text here now",
        3123,
        1
    )

    NoteService.create(note1)
    NoteService.create(note2)

    println(NoteService.getList())

    NoteService.edit(updatedNote1)
    println(NoteService.getList())

    NoteService.delete(1)
    println(NoteService.getList())

    NoteService.create(note1)
    println(NoteService.getList())
}