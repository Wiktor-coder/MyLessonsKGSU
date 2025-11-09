package nyetHack

import kotlin.math.pow

fun main() {
    val name = "Marginal"
    var healthPoints = 89 // здоровье
    val isBlessed = true //  благословенный
    val isImmortal = false // бесмертие
    val race = "gnome"

    // Расса
    val faction = when (race) {
        "dwarf", "gnome" -> "Keepers of the Mines"
        "orc" -> "Free People of the Rolling Hills"
        "human" -> "Free People of the Rolling Hills"
        else -> ""
    }

    // Аура
    val karma = (Math.random().pow((110 - healthPoints) / 100.0) * 20).toInt()
    val colorAura = when(karma) {
        in 0..5 -> "RED"
        in 6..10 -> "ORANGE"
        in 11..15 -> "PURPLE"
        in 16..20 -> "GREEN"
        else -> "BLUE"
    }
    println("Aura colorAura: $colorAura")

    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val auraColor = if (auraVisible) "GREEN" else "NONE"
    println(
        "(Aura: $auraColor) " +
                "(Blessed: ${if (isBlessed) "YES" else "NO"})"
    )

    // Рефактинг healthStatus на When вместо if
    val healthStatus = when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else {
            "has some minor wounds."
        }

        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }

    // Оставлю для примера
//    val healthStatus = if (healthPoints == 100) {
//        "is in excellent condition!"
//    } else if (healthPoints in 90..99) {
//        "has a few scratches."
//    } else if (healthPoints in 75..89) {
//        if (isBlessed) {
//            "has some minor wounds but is healing quite quickly!"
//        } else {
//            "has some minor wounds."
//        }
//    } else if (healthPoints in 15..74) {
//        "looks pretty hurt."
//    } else {
//        "is in awful condition!"
//    }
    println("$name $healthStatus $faction")

    val statusFormatString = "(HP: $healthPoints) " +
            "(Aura: $auraColor) " +
            "(Blessed: ${if (isBlessed) "YES" else "NO"}) " +
            "-> $name $healthStatus"
    println(statusFormatString)
}