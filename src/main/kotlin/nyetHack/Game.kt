package nyetHack

fun main() {
    val name = "Marginal"
    var healthPoints = 89 // здоровье
    val isBlessed = true //  благословенный
    val isImmortal = false // бесмертие
    val race = "gnome"

    //передовая функции как аргументы избавились от части кода и ненужных переменных
//    // Расса рефакторинг в функцию
//    val faction = getFaction(race)
//
//    // Аура рефакторинг в функцию
//    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)
//
//    // Рефактинг healthStatus вынос логики в отдельную функцию
//    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    // рефакторинг на вывод статуса
    printPlayerStatus(
        auraColor(isBlessed, healthPoints, isImmortal),
        isBlessed,
        name,
        formatHealthStatus(healthPoints, isBlessed),
        getFaction(race)
    )



    // создаём обьект класса чтобы воспользоватся функцией
    val combat = Combat()
    // вызываем Функцию класса Combat
    combat.performCombat()

}

// функцияпринадлежности к рассе
private fun getFaction(race: String): String = when (race) {
    "dwarf", "gnome" -> "Keepers of the Mines"
    "orc" -> "Free People of the Rolling Hills"
    "human" -> "Free People of the Rolling Hills"
    else -> ""
}

// функция для вывода статуса
private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String,
    faction: String
) {
    println(
        "(Aura: $auraColor) " +
                "(Blessed: ${if (isBlessed) "YES" else "NO"})"
    )
    println("$name $healthStatus $faction")
}

// функция на цвет ауры
private fun auraColor(
    isBlessed: Boolean,
    healthPoints: Int,
    isImmortal: Boolean
): String = if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"


// Рефактинг healthStatus на When вместо if
private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean): String = when (healthPoints) {
    100 -> "is in excellent condition!"
    in 90..99 -> "has a few scratches."
    in 75..89 -> getWoundDescription(isBlessed)
    in 15..74 -> "looks pretty hurt."
    else -> "is in awful condition!"
}

private fun getWoundDescription(isBlessed: Boolean): String = if (isBlessed) {
    "has some minor wounds but is healing quite quickly!"
} else {
    "has some minor wounds."
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

// заклинание бокал вина
private fun castFireball(numFireballs: Int = 2): String {
    println("A glass of Fireball springs into existence.(x$numFireballs)")
    var intoxicatingLevel = numFireballs * 3
    if (intoxicatingLevel > 50) {
        intoxicatingLevel = 50
    }
    val intoxicatedState = when (intoxicatingLevel) {
        in 1..10 -> "Tipsy" //навеселе
        in 11..20 -> "Sloshed" // выпивший
        in 21..30 -> "Soused" //пьяный
        in 31..40 -> "Stewed" //сильно пьяный
        in 41..50 -> "..t0aSt3d" //в стельку
        else -> "Dead"
    }
    return intoxicatedState
}
