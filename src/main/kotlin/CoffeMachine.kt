package machine

class CoffeMachine(
    private var currentWatter: Int = 400,
    private var currentMilk: Int = 540,
    private var currentCoffeBeans: Int = 120,
    private var cups: Int = 9,
    private var money: Int = 550
){

    fun getWater() = currentWatter

    fun getMilk() = currentMilk

    fun getCoffeBeans() = currentCoffeBeans

    fun getCups() = cups

    fun getMoney() = money

    fun fill(water: Int, milk: Int, coffeBeans: Int, cupsParam: Int){

        currentWatter += water
        currentMilk += milk
        currentCoffeBeans += coffeBeans
        cups += cupsParam

    }

    fun take(): Int{

        val preMoney = money
        money = 0
        return preMoney

    }

    fun makeCoffe (coffeType : Int): CoffeStatusCode{

        //Si hay zero de agua o leche o bolsas de cafe el programa truena jajaj xd

        if(cups == 0){
            return CoffeStatusCode(errorCode = 1, "disposable cups")
        }else{
            val coffeNeeds = getCoffeNeeds(coffeType)
            val watterNedeed = coffeNeeds.water
            val milkNedeed = coffeNeeds.milk
            val coffeBeans = coffeNeeds.coffeBeans

            if(watterNedeed > currentWatter){
                return CoffeStatusCode(errorCode = 1, "water")
            }else if (milkNedeed > currentMilk){
                return CoffeStatusCode(errorCode = 1, "milk")
            }else if (coffeBeans > currentCoffeBeans){
                return CoffeStatusCode(errorCode = 1, "coffe beans")
            }else{
                val totalWater = watterNedeed / currentWatter
                val totalMilk = milkNedeed / currentMilk
                val totalCoffeBeans = coffeBeans / currentCoffeBeans

                val coffes = coffesCanMake(totalWater, totalMilk, totalCoffeBeans)

                if(coffes > cups){
                    return CoffeStatusCode(errorCode = 2, "Not enough supplies!")
                }else{
                    currentWatter -= watterNedeed
                    currentMilk -= milkNedeed
                    currentCoffeBeans -= coffeBeans
                    cups -= 1
                    money += coffeNeeds.price
                    return CoffeStatusCode(message = "I have enough resources, making you a coffee!")
                }
            }
        }
    }

    fun coffesCanMake(water: Int, milk: Int, coffeBeans : Int): Int{

        return if(water <= milk){
            if(water <= coffeBeans){
                water
            }else{
                coffeBeans
            }
        }else if(milk <= coffeBeans) {
            milk
        }else {
            coffeBeans
        }

    }

    fun getCoffeNeeds(coffeType: Int) = when(coffeType) {
        1 -> CoffeNeeds(WATER_NEDEED_ESPRESSO,
            MILK_NEDEED_ESPRESSO,
            COFFEE_BEANS_NEDEED_ESPRESSO,
            ESPRESSO_PRICE)
        2 -> CoffeNeeds(WATER_NEDEED_LATTE,
            MILK_NEDEED_LATTE,
            COFFEE_BEANS_NEDEED_LATTE,
            LATTE_PRICE)
        else -> CoffeNeeds(WATER_NEDEED_CAPPUCCINO,
            MILK_NEDEED_CAPPUCCINO,
            COFFEE_BEANS_NEDEED_CAPPUCCINO,
            CAPPUCCINO_PRICE)
    }

}



