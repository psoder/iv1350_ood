import java.util.Random
import kotlin.collections.Iterator

/**
 * Prints five random characthers.
 */
fun main() {
    val randomat = RandomChar()
    val charMap = HashMap<Int, Char>()

    for (i in 1..5) {
        charMap[i] = randomat.nextChar()
    }

    val mi = MapIterator(charMap)

    while (mi.hasNext()) {
        print("${mi.next()} ")
    }
}

/**
 * Extension of the Random-class that supports random characthers.
 */
class RandomChar : Random() {

    /**
     * Returns a random characther.
     * 
     * @return a random char.
     */
    fun nextChar(): Char = nextInt().toChar()
}

/**
 * Iterates over a Map. Implements the iterator interface.
 */
class MapIterator<K, V>(val map: Map<K, V>) : Iterator<V> {

    private val keys = ArrayDeque(map.keys)

    /**
     * Returns true if the iteration has more elements. (In other words,
     * returns true if next() would return an element rather than throwing an
     * exception.)
     * 
     * @return true if the iteration has more elements
     */
    override fun hasNext(): Boolean = keys.any()

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @throws NoSuchElementException if the iteration has no more elements
     */
    override fun next(): V = map[keys.removeFirst()]!!
}
