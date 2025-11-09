package nyetHack

data class Drink(
    val beer: String = "beer",
    val sprat: String = "sprat",
) {
    companion object {
        fun fromInput(input: String?): String = when (input?.trim()?.lowercase()) {
            Drink().beer -> "beer drink"
            Drink().sprat -> "sprat drink"
            else -> "not drink"
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

    var num = 0
    while (num < 3) {
        println(Drink.fromInput(readlnOrNull()))
        num++
    }

    //println(Drink.fromInput(readlnOrNull()))
}
