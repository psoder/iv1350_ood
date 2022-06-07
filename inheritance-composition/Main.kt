import java.util.Random
import kotlin.collections.Iterator

/**
 * Prints five random characthers.
 */
fun main() {
    val randomInheritance = RandomCharInheritance()
    val randomComposition = RandomCharComposition()

    println("With inheritance")
    for (i in 1..5) {
        print("${randomInheritance.nextChar()} ")
    }

    println("\n\nWith composition")

    for (i in 1..5) {
        print("${randomComposition.nextChar()} ")
    }
    println()
}

/**
 * Extension of the Random-class that supports random characthers.
 */
class RandomCharInheritance : Random() {

    /**
     * Returns a random characther.
     * 
     * @return a random char.
     */
    fun nextChar(): Char = nextInt().toChar()
}

class RandomCharComposition {

    val random = Random()

    /**
     * Returns a random characther.
     *
     * @return a random char.
     */
    fun nextChar(): Char = random.nextInt().toChar()
}
