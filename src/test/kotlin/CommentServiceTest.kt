import org.junit.Assert.assertTrue
import org.junit.Test

class CommentServiceTest {

    private val testComment1 = Comment(
        1,
        100,
        "test message"
    )
    private val testCommentUpdated1 = Comment(
        1,
        100,
        "test message updated",
        1
    )
    private val testCommentUpdated2 = Comment(
        1,
        100,
        "test message updated wrong",
        2
    )
    private val testCommentDeleted = Comment(
        1,
        100,
        "this comment already deleted",
        deleted = true
    )

    @Test
    fun delete_commentFound() {
        CommentService.clearList()
        CommentService.create(testComment1)

        val result = CommentService.delete(1)
        assertTrue(result)
    }

    @Test(expected = CommentService.CommentNotFoundException::class)
    fun delete_commentNotFound() {
        CommentService.clearList()
        CommentService.create(testComment1)

        CommentService.delete(2)
    }

    @Test(expected = CommentService.CommentWasAlreadyDeleted::class)
    fun delete_commentAlreadyDeleted() {
        CommentService.clearList()
        CommentService.create(testCommentDeleted)

        CommentService.delete(1)
    }

    @Test
    fun edit_commentFound() {
        CommentService.clearList()
        CommentService.create(testComment1)

        val result = CommentService.edit(testCommentUpdated1)
        assertTrue(result)
    }

    @Test(expected = CommentService.CommentNotFoundException::class)
    fun edit_commentNotFound() {
        CommentService.clearList()
        CommentService.create(testComment1)

        CommentService.edit(testCommentUpdated2)
    }

    @Test
    fun restore_commentFound() {
        CommentService.clearList()
        CommentService.create(testCommentDeleted)

        val result = CommentService.restore(1)
        assertTrue(result)
    }

    @Test(expected = CommentService.CommentNotFoundException::class)
    fun restore_commentNotFound() {
        CommentService.clearList()
        CommentService.create(testCommentDeleted)

        CommentService.restore(2)
    }

    @Test(expected = CommentService.CommentIsntDeleted::class)
    fun restore_commentNotDeleted() {
        CommentService.clearList()
        CommentService.create(testComment1)

        CommentService.restore(1)
    }
}