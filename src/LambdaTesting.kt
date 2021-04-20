class NetworkingStuff() {
    fun getApiData(): String {
        println("Do some stuff in getApiData")
        return "Real answer from get API Data"
    }
}

class MainClass(private val networkingStuffInstance: NetworkingStuff) {
    fun doStuff(): String {
        println("Do stuff in main class")
        return networkingStuffInstance.getApiData()
    }
}

/**
 *  Example test using injected class:
 *  val mockedNetworkStuff = mock<NetworkingStuff>()
 *  wehenever(mockedNetworkStuff.getApiData()).thenReturn("Fake answer")
 *
 *  fun testNetworkingStuff() {
 *      ...
 *  }
 */

fun main() {
    // Create an instance outside the MainClass to inject in
    val networkingStuffInstance = NetworkingStuff()

    // Create main class passing in the instance then call doStuff function
    val mainClass = MainClass(networkingStuffInstance)
    println(mainClass.doStuff())


    /**
     * In the example above, as NetworkingStuff is being passed into MainClass, when testing this
     * class, NetworkingStuff can be mocked to return whatever is necessary. This is good practise
     * and works well but can be done using lambdas.
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * In the example below, there is no NetworkingStuff class being passed into MainClass. Instead
     * there is a lambda being passed in using a typealias (which would likely live in the same
     * file as MainClassTakingLambda, at the bottom). When networking stuff is required, the
     * lambda is invoked which then calls for the content to be run inside the lambda and
     * the value returned.
     */

    val networkingStuffLambda: (String) -> String = { string ->
        "Real answer from Lambda $string"
    }

    val mainClassLambdaInstance = MainClassTakingLambda(networkingStuffLambda)
    println(mainClassLambdaInstance.doStuff())
}

class MainClassTakingLambda(private val networkLambda: NetworkLambda) {
    fun doStuff(): String {
        println("Do some stuff in main class taking lambda")
        return networkLambda.invoke("Do some stuff")
    }
}

typealias NetworkLambda = (string: String) -> String

/**
 *  Example test using lambda:
 *  val mockedNetworkLambda = mock<NetworkLambda>()
 *  wehenever(mockedNetworkLambda.invoke(any())).thenReturn("Fake answer")
 *
 *  fun testNetworkingStuff() {
 *      ...
 *  }
 */
