class ClassC() {
    fun startingLogic() {
        val classDInstance = ClassD()
        classDInstance.doStuffButDontReturn { i: Int, s: String ->
            println("Message in ClassC but from ClassD: $s")
            endingLogic(i)
        }
    }

    private fun endingLogic(i: Int) {
        println("All finished with ClassC: $i")
    }
}

class ClassD() {
    fun doStuffButDontReturn(callback: (Int, String) -> Unit) {
        println("Doing stuff in class D")
        callback.invoke(1, "Finished in ClassD")
    }
}

fun main() {
    ClassC().startingLogic()
}
