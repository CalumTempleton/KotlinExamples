import kotlin.properties.Delegates

interface ValueChangedListener {
    fun onValueChanged(newValue: String)
}

class PrintTextChangedListener: ValueChangedListener {
    override fun onValueChanged(newValue: String) {
        println("Text is changed to $newValue")
    }
}

class ObservableObject(listener: ValueChangedListener){
    var text: String by Delegates.observable(
        initialValue = "",
        onChange = {
            property, oldValue, newValue ->
            listener.onValueChanged(newValue)
        }
    )
}

fun main(args: Array<String>) {
    val observableObject = ObservableObject(PrintTextChangedListener())
    observableObject.text = "Howdy!"
    observableObject.text = "How Y'all been?"
}

// We have an observable object called text. This is observed by Delegates.observerable which essentially tracks the
// variable and its history. It has an initial value which is assigned when it is first declared, property which can
// have a callback, the oldValue and the newValue. The observable object is part of class which takes a listener in
// its constructor. This implemented an interface which itself needs to be implemented which is done through the
// PrintTextChangedListener class. This is all linked nicely together in the main function.