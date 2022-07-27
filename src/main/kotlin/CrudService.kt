abstract class CrudService<T> {
    protected val elements = mutableListOf<T>()
    protected var elementId = 0

    abstract fun create(elem: T): Int
    abstract fun getById(id: Int): T?
    abstract fun edit(elem: T): Boolean
    abstract fun delete(id: Int): Boolean

    fun getList(): MutableList<T> {
        return elements
    }

    //for testing purposes
    fun clearList() {
        elementId = 0
        elements.clear()
    }
}