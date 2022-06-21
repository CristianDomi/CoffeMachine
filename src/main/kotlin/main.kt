package machine

fun main() {

    val coffeMachine = CoffeMachine()

    do {
        println("Write action (buy, fill, take, remaining, exit):")
        val action = readln()
        when(action){
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                val buyAction = readln()
                if (buyAction != "back"){
                    val result = coffeMachine.makeCoffe(buyAction.toInt())
                    when (result.errorCode){
                        0,2 -> println(result.message)
                        1 -> printNoSuplies(result.message)
                    }
                }
            }
            "fill" -> {
                println("Write how many ml of water do you want to add:")
                val fillWater = readln().toInt()
                println("Write how many ml of milk do you want to add:")
                val fillMilk = readln().toInt()
                println("Write how many grams of coffee beans do you want to add:")
                val fillCoffeBeans = readln().toInt()
                println("Write how many disposable cups of coffee do you want to add:")
                val fillCups = readln().toInt()
                println()
                coffeMachine.fill(water = fillWater, milk = fillMilk, coffeBeans = fillCoffeBeans, cupsParam = fillCups)
            }
            "take" -> {
                println("I gave you ${coffeMachine.take()}")
                println()
            }
            "remaining" -> {
                println("The coffee machine has:")
                println("${coffeMachine.getWater()} ml of water")
                println("${coffeMachine.getMilk()} ml of milk")
                println("${coffeMachine.getCoffeBeans()} g of coffee beans")
                println("${coffeMachine.getCups()} disposable cups")
                println("$${coffeMachine.getMoney()} of money")
                println()
            }
        }
    } while (action != "exit")

}

fun printNoSuplies(supply : String) = println("Sorry, not enough $supply!")