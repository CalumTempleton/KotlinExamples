import java.util.*
import kotlin.random.Random.*

// The Kotlin standard library contains several functions whose sole purpose is to execute a block
// of code within the context of an object. When you call such a function on an object with a
// lambda expression provided, it forms a temporary scope. In this scope, you can access the
// object without its name. Such functions are called scope functions. There are five of them:
// let, run, with, apply, and also.

// Basically, these functions do the same: execute a block of code on an object. What's different
// is how this object becomes available inside the block and what is the result of the whole
// expression.

fun main() {
    data class Person(var name: String,
                      var age: Int,
                      var city: String) {
        fun moveTo(newCity: String) { city = newCity }
        fun incrementAge() { age++ }
    }

    // An example of a scope function would be
    Person("Alice", 20, "Amsterdam").let {
        println(it)
        it.moveTo("London")
        it.incrementAge()
        println(it)
    }

    // You can write the same without let but you'll have to introduce a new variable and repeat
    // its name whenever you use it.
    val alice = Person("Alice", 20, "Amsterdam")
    println(alice)
    alice.moveTo("London")
    alice.incrementAge()
    println(alice)

    // The scope functions do not introduce any new technical capabilities, but they can make your
    // code more concise and readable. Due to the similar nature of scope functions, choosing the
    // right one for your case can be a bit tricky. The choice mainly depends on your intent and
    // the consistency of use in your project.

    // There are two main differences between each scope function:
    // - the way to refer to the context
    // - object and the return value

    // Context object: this or it
    // Inside the lambda of a scope function, the context object is available by a short reference
    // instead of its actual name. Each scope function uses one of two ways to access the context
    // object: as a lambda receiver (this) or as a lambda argument (it). Both provide the same
    // capabilities, so we'll describe the pros and cons of each for different cases and provide
    // recommendations on their use.

    val str = "Hello"
    // this
    str.run {
        println("The receiver string length: ${this.length}") // Note this isn't needed here
    }

    // it
    str.let {
        println("The receiver string's length is ${it.length}")
    }

    // this
    // run, with, and apply refer to the context object as a lambda receiver - by keyword this.
    // Hence, in their lambdas, the object is available as it would be in ordinary class
    // functions. In most cases, you can omit this when accessing the members of the receiver
    // object, making the code shorter. On the other hand, if this is omitted, it can be hard to
    // distinguish between the receiver members and external objects or functions. So, having the
    // context object as a receiver (this) is recommended for lambdas that mainly operate on the
    // object members: call its functions or assign properties.

    data class Person2(var name: String, var age: Int = 0, var city: String = "")

    val adam = Person2("Adam").apply {
        age = 20 // same as this.age = 20 or adam.age = 20
        city = "London"
    }
    println(adam)

    // it
    // In turn, let and also have the context object as a lambda argument. If the argument name is
    // not specified, the object is accessed by the implicit default name it. it is shorter than
    // this and expressions with it are usually easier for reading. However, when calling the
    // object functions or properties you don't have the object available implicitly like this.
    // Hence, having the context object as it is better when the object is mostly used as an
    // argument in function calls. it is also better if you use multiple variables in the code block.
    // You can also specify custom names for it

    fun writeToLog(message: String) {
        println("INFO: $message")
    }

    fun getRandomInt(): Double {
        return Math.random().also { randomNumber ->
            writeToLog("getRandomInt() generated value $randomNumber")
        }
    }
    val i = getRandomInt()

    // At return value - https://kotlinlang.org/docs/reference/scope-functions.html
}
