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

    println("created list of posts:")
    println(NoteService.getList())

    NoteService.edit(updatedNote1)
    println("first post was updated:")
    println(NoteService.getList())

    NoteService.delete(1)
    println("the first post was deleted:")
    println(NoteService.getList())

    NoteService.create(note1)
    println("the first post is now added again:")
    println(NoteService.getList())

    //Comment system

    val comment1 = Comment(
    2,
    354,
    "hi, i'm commenting!",
    )

    val comment2 = Comment(
        2,
        2341,
        "hi, i'm commenting on your comment!",
        replyTo = 1
    )

    val comment1Updated = Comment(
        2,
        354,
        "and now i've updated my comment!",
        1,
    )

    val comment3 = Comment(
        3,
        623,
        "just leaving some comments"
    )

    NoteService.createComment(comment1)
    NoteService.createComment(comment2)
    NoteService.createComment(comment3)

    println("added two comments to first post and one to second")
    println(NoteService.getList())

    NoteService.editComment(comment1Updated)
    println("updated the first comment of the first post")
    println("showing only the comments for the first post:")
    println("amount of comments - "+NoteService.getById(2)!!.comments)
    println(NoteService.getComments(2))

    NoteService.deleteComment(2)
    println("deleted the second comment on first post:")
    println("amount of comments - "+NoteService.getById(2)!!.comments)
    println(NoteService.getComments(2))

    NoteService.restoreComment(2)
    println("restored the second comment on first post:")
    println("amount of comments - "+NoteService.getById(2)!!.comments)
    println(NoteService.getComments(2))
}