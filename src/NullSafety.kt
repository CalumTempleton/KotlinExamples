// Null safety makes an app more robust and safe. Using these operators help us avoid null pointer exceptions.
// There are also other methods that can be used with nulls such as lateinit and lazy.
// There are many kinds of null safety operators: Safe Call (?.), Elvis (?:), Safe Call with let (?.let {...}) and
// Not-null Assertion/Double Bang (!!)

fun main(args: Array<String>) {
    // Traditionally in Java, a variable could be assigned null. In Kotlin this is not
    // allowed by default to reduce null pointer exceptions. To allow for null to be assigned,
    // we need to make variables nullable or the compile will complain. This is done by using a
    // question mark (?).

    // var name: String = null - Not allowed
    var name: String? = null // Allowed
    val realName: String? = "Ben"

    // To make use of nullable variables, a null safety operator must be used or the compiler
    // will highlight the issue.

    // Safe Call Operator (?.)
    // Using the 'name' variable above and trying to get the length, the length will only be
    // returned if 'name' is not null. If the value is null, the operation on the right is simply
    // ignored and the value is returned, which in this case is null.

    val size1 = name?.length // Will return null
    val size2 = realName?.length // Will return 3

    // Elvis Operator (?:)
    // When we have nullable reference 'name', we can say "is name not null", use it, otherwise
    // use some non-null value (a default). The non-null value in this case is -1. The two
    // snippets of code below produce the same output.

    val len1 = if (name != null) {
        name.length
    } else {
        -1
    }
    val len2 = name?.length ?: -1 // Note that ?: acts as an else to default value

    // Safe Call with let (?.let {...})
    // A safe call let block only executes if the name is not null.

    var ten: String? = null
    ten?.let{
        println(ten) // Wouldn't print as value starts as null
    }
    ten = "ten"
    ten?.let{ // Note ? is not needed here but if ten was assigned to a function, it wouldn't know
        println(ten) // Would print as value is not null
    }

    // Not-null Assertion/Double Bang (!!)
    // A non-null assertion should only be used when you are sure the value will not be null.
    // This will throw a nullpoitner exception if the value is found to be null.
    val len = ten!!.length

    //Lateinit
    // With Kotlin, you can assign variables at a later stage in your code, not where the variable
    // is defined. Previously, you could have set the variable to null, however, this is not good
    // practise in Koltin.

    var name1: String // You can't do this as the compiler wants a value for the variable
    var name2: String = "Dummy" // Will work but what happens if we don't want to define this value here
    lateinit var name3: String // This is allowed

    // By using the lateinit keyword, we enter a contract with the compiler saying the you will
    // initalise the variable with a value before using it. If you do not set a value and try to
    // access the variable before it is initalised, you will get an UninitializedPropertyAccessException.
    // Lateinit can only be used with mutable data types (var) and therefore cannot be final (val).
    // Lateinit can also only be used with non-nullable data types (ie no :String?, only :String).

    // Lazy Initialization
    // Lazy is a lambda function which is designed to prevent unnecessary initialization of
    // objects which wastes memory. Lazy variables will not be initialized unless you use it in
    // your code making effective use of memory allocation. Lazy variables are only initialized
    // once meaning the second/next time you use it, it gets the value from the cache memory.

    // Lazy Initialization is thread safe and is initialized where it is used for the first time.
    // If the value is accessed from another thread, it will be taken from the cache memory.
    // You can use lazy with var or val and nullable or non-nullable types.

    val pi: Float by lazy { 3.14f }
}
