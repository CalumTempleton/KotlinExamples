// When you need to add some behavior to a class you have two choices:
// - extend it and create a subclass (only when extendable)
// - use the decorator pattern to decorate the class with the new functionality

// The decorator pattern is used to extend or alter the functionality of objects at run-time by
// wrapping them in an object of a decorator class. This provides a flexible alternative to using
// inheritance to modify behaviour

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() = println("Normal: Making small coffee")

    override fun makeLargeCoffee() = println("Normal: Making large coffee")
}

//Decorator: note the by is used here as we are inheriting from the interface and as the methods
// don't match up, if we make use of coffeeMachine passed in, we don't need to write a new
// implementation for makeSmallCoffee as we can use the coffeeMachine object passed in
class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {

    // overriding behaviour
    override fun makeLargeCoffee() {
        println("Enhanced: Making large coffee")
        coffeeMachine.makeLargeCoffee()
    }

    // extended behaviour
    fun makeCoffeeWithMilk() {
        println("Enhanced: Making coffee with milk")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced: Adding milk")
    }
}

fun main() {
    val normalMachine = NormalCoffeeMachine()
    val enhancedMachine = EnhancedCoffeeMachine(normalMachine)

    // non-overridden behaviour
    enhancedMachine.makeSmallCoffee()
    // overriding behaviour
    enhancedMachine.makeLargeCoffee()
    // extended behaviour
    enhancedMachine.makeCoffeeWithMilk()

    /**
     * Another example is shown below
     */

    val vanillaIceCreamWithHoneyTopping = HoneyToppingDecorator(VanillaIceCream())
    val vanillaIceCreamWithHoneyToppingDescription = vanillaIceCreamWithHoneyTopping.getDescription()
    println("You ordered: ${vanillaIceCreamWithHoneyToppingDescription.trimEnd('&')}")

    val strawberryCreamWithNutsTopping = NutsToppingDecorator(StrawberryIceCream())
    val strawberryCreamWithNutsToppingDescription = strawberryCreamWithNutsTopping.getDescription()
    println("You ordered: ${strawberryCreamWithNutsToppingDescription.trimEnd('&')}")

    val chocolateIceCreamWithHoneyAndNutsTopping = NutsToppingDecorator(HoneyToppingDecorator(ChocolateIceCream()))
    val chocolateIceCreamWithHoneyAndNutsToppingDescription = chocolateIceCreamWithHoneyAndNutsTopping.getDescription()
    println("You ordered: ${chocolateIceCreamWithHoneyAndNutsToppingDescription.trimEnd('&')}")
}

abstract class IceCream {
    abstract fun getDescription(): String
}

class VanillaIceCream : IceCream() {
    override fun getDescription() = "Vanilla ice cream"
}

class StrawberryIceCream : IceCream() {
    override fun getDescription() = "Strawberry ice cream"
}

class ChocolateIceCream : IceCream() {
    override fun getDescription() = "Chocolate ice cream"
}

abstract class ToppingDecorator() : IceCream() {
    abstract fun addTopping(): String
}

class HoneyToppingDecorator(private val iceCream: IceCream) : ToppingDecorator() {
    override fun getDescription() = iceCream.getDescription() + addTopping()
    override fun addTopping() = " With honey topping &"
}

class NutsToppingDecorator(private val iceCream: IceCream) : ToppingDecorator() {
    override fun getDescription() = iceCream.getDescription() + addTopping()
    override fun addTopping() = " With nuts topping &"
}
