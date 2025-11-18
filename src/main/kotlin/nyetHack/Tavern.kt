package nyetHack

import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

data class Drink(
    val beer: String = "beer",
    val sprat: String = "sprat",
) {
    companion object {
        fun fromInput(input: String?): String = when (input?.trim()?.lowercase() ?: "fack of this is not drink") {
            Drink().beer -> "beer drink"
            Drink().sprat -> "sprat drink"
            else -> "not drink $input"
        }
    }
}

var playerGold = 0
var playerSilver = 0
var dracoinCoins = 5

fun main() {
//    val beverage = readlnOrNull()
//    val drink = Drink()
//    val result = when (beverage) {
//        drink.beer -> "beer drink"
//        drink.sprat -> "sprat drink"
//        else -> "not drink"
//    }
//    println(result)
//
//    println(
//        when (beverage) {
//            "beer" -> "beer drink"
//            "sprat" -> "sprat drink"
//            else -> "not drink"
//        }
//    )
//    var beverage = readLine()?.let {
//        if (it.isNotBlank()) { // проверяет строку на символы, isNotEmpty() проверяет длину строки
//            it.lowercase()
//        } else {
//            "is not drink"
//        }
//    }
//
//    var num = 0
//    while (num < 3) {
//        println(Drink.fromInput(readlnOrNull()))
//        num++
//    }

    //println(Drink.fromInput(readlnOrNull()))
    placeOrder("shandy,Dragon's Breath,5.91")
//    placeOrder("elixir,Shirley's Temple,4.12")

}

fun performPurchase(price: Double) {
    displayBalance()
    val totalPurse = (dracoinCoins * 1.43) + playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")

    if (totalPurse >= price) {
        val remainingBalance = totalPurse - price
        println("Remaining balance: ${"%.2f".format(remainingBalance / 1.43)}")

        val remainingGold = remainingBalance.toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerGold = remainingGold
        playerSilver = remainingSilver
    } else {
        println("insufficient funds")  // недостаточно средств
    }
    displayBalance()
}

fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold, Silver: $playerSilver")
}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    /*Так как split() возвращает список, для нее поддерживается упрощенный синтаксис,
    который называется деструктуризацией. Деструктуризация - это возможность объявить
    и инициализировать сразу несколько переменных*/
    val (type, name, price) = menuData.split(',').map { it.trim() }
    println("Madrigal buys a $name ($type) for $price.")

    performPurchase(price.toDouble())
//    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims: ${("Ah, delicious $name!").toDragonSpeak()}"
    } else {
        "Madrigal says: Thanks for the $name."
    }
    println(phrase)
}

private fun String.toDragonSpeak() =
    replace(Regex("[aeiouAEIOU]")) { /* Regex сравнивает передоваемую строку
    с указанными символами*/
        when (it.value.lowercase()) { // значение регистра строки не поменяется это локально для сравнения
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }
