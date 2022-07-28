import org.junit.Assert.*
import org.junit.Test

class NoteServiceTest {

    private val testNote1 = Note(
        100,
        "Test note",
        "Test note text",
        200
    )

    private val testNoteEdit1 = Note(
        100,
        "Test note update",
        "Test note text updated",
        300,
        id = 1
    )
    private val testNoteEdit2 = Note(
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

    //Commnt system test
    private val testComment1 = Comment(
        1,
        100,
        "test message"
    )
    private val testComment2 = Comment(
        2,
        100,
        "test message"
    )
    private val testCommentUpdated1 = Comment(
        1,
        100,
        "test message updated",
        1
    )
    private val testCommentDeleted = Comment(
        1,
        100,
        "this comment already deleted",
        deleted = true
    )

    @Test
    fun createComment_NoteFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        val result = NoteService.createComment(testComment1)
        assertEquals(result,1)
    }

    @Test (expected = NoteService.NoteNotFoundException::class)
    fun createComment_NoteNotFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        NoteService.createComment(testComment2)
    }


    @Test
    fun deleteComment_NoteFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        NoteService.createComment(testComment1)
        val result = NoteService.deleteComment(1)
        assertTrue(result)
    }

    //This one happens when the note the comment was added to is deleted already
    @Test (expected = NoteService.NoteNotFoundException::class)
    fun deleteComment_NoteNotFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        NoteService.createComment(testComment1)
        NoteService.delete(1)
        NoteService.deleteComment(1)
    }


    @Test
    fun editComment_NoteFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        NoteService.createComment(testComment1)
        val result = NoteService.editComment(testCommentUpdated1)
        assertTrue(result)
    }

    //This one happens when the note the comment was added to is deleted already
    @Test (expected = NoteService.NoteNotFoundException::class)
    fun editComment_NoteNotFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        NoteService.createComment(testComment1)
        NoteService.delete(1)
        NoteService.editComment(testCommentUpdated1)
    }

    @Test
    fun getComments_NoteFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        NoteService.createComment(testComment1)
        val result = NoteService.getComments(1)
        assertEquals(result,mutableListOf<Comment>(testComment1.copy(id=1)) )
    }

    @Test (expected = NoteService.NoteNotFoundException::class)
    fun getComments_NoteNotFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        NoteService.createComment(testComment1)
        NoteService.getComments(2)
    }

    @Test
    fun restoreComment_NoteFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        NoteService.createComment(testCommentDeleted)
        val result = NoteService.restoreComment(1)
        assertTrue(result)
    }

    //This one happens when the note the comment was added to is deleted already
    @Test (expected = NoteService.NoteNotFoundException::class)
    fun restoreComment_NoteNotFound() {
        NoteService.clearList()
        CommentService.clearList()
        NoteService.create(testNote1)

        NoteService.createComment(testCommentDeleted)
        NoteService.delete(1)
        NoteService.restoreComment(1)
    }
}