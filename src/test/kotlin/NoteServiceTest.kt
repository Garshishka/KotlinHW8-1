import org.junit.Assert.assertTrue
import org.junit.Test

class NoteServiceTest {

    val testNote1 = Note(
        100,
        "Test note",
        "Test note text",
        200
    )

    val testNoteEdit1 = Note(
        100,
        "Test note update",
        "Test note text updated",
        300,
        id = 1
    )
    val testNoteEdit2 = Note(
        100,
        "Test note wrong update",
        "Test note wrong text updated",
        400,
        id = 2
    )


    @Test
    fun edit_noteFound() {
        NoteService.clearList()
        NoteService.create(testNote1)

        val result = NoteService.edit(testNoteEdit1)
        assertTrue(result)
    }

    @Test(expected = NoteService.NoteNotFoundException::class)
    fun edit_noteNotFound() {
        NoteService.clearList()
        NoteService.create(testNote1)

        NoteService.edit(testNoteEdit2)
    }

    @Test
    fun delete_noteFound() {
        NoteService.clearList()
        NoteService.create(testNote1)

        val result = NoteService.delete(1)
        assertTrue(result)
    }

    @Test(expected = NoteService.NoteNotFoundException::class)
    fun delete_noteNotFound() {
        NoteService.clearList()
        NoteService.create(testNote1)

        NoteService.delete(2)
    }
}