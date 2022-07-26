abstract class CrudService<T> {
    protected val elements = mutableListOf<T>()
    protected var elementId = 0

    abstract fun create(elem: T): T
    abstract fun findById(id: Int): T?
    abstract fun edit(elem: T): Boolean
    abstract fun delete(id: Int): Boolean

    fun getList(): MutableList<T> {
        return elements
    }

    //for testing purposes
    fun clearList() {
        elements.clear()
    }
}