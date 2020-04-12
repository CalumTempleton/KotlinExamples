// Asynchrony, in computer programming, refers to the occurrence of events independent of the main
// program flow and ways to deal with such events, … without the program blocking to wait for them


// Calling class which makes the request
class ClassA {
    private val responseCallback= object : ICallback { // The callback implements the interface
        override fun printCallback(string: String) {
            println(string)
        }
    }

    fun helloWorld() {
        val callback = ClassB().doSomethingInAnotherClass(responseCallback)
    }
}

// Class that can fufill the callback
class ClassB {
    fun doSomethingInAnotherClass(responseCallback: ICallback){ // Note interface
        responseCallback.printCallback("Hello world from a callback")
    }
}

interface ICallback {
    fun printCallback(string: String)
}

fun main(args: Array<String>) {
    ClassA().helloWorld()
}