// Asynchrony, in computer programming, refers to the occurrence of events independent of the main
// program flow and ways to deal with such events, without the program blocking to wait for them


// Class which makes the request
class ClassA {
    private val responseCallback= object : IPrintingFunctions { // The callback implements the interface
        override fun printCallback(string: String) {
            println(string)
        }
    }

    fun sayHelloWorld() {
        ClassB().sayHelloFromAnotherClass(responseCallback)
    }
}

// Class that fulfills the callback
class ClassB {
    fun sayHelloFromAnotherClass(responseCallback: IPrintingFunctions){
        responseCallback.printCallback("Hello world, $SPECIAL_VALUE_UNIQUE_TO_B_CLASS")
    }

    private companion object {
        private const val SPECIAL_VALUE_UNIQUE_TO_B_CLASS = "how are things?"
    }
}

interface IPrintingFunctions {
    fun printCallback(string: String)
}

fun main() {
    ClassA().sayHelloWorld()
}
