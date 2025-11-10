package nyetHack

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
    var beverage = readLine()?.let {
        if (it.isNotBlank()) { // проверяет строку на символы, isNotEmpty() проверяет длину строки
            it.lowercase()
        } else {
            "is not drink"
        }
    }

    var num = 0
    while (num < 3) {
        println(Drink.fromInput(readlnOrNull()))
        num++
    }

    //println(Drink.fromInput(readlnOrNull()))
}
