package nyetHack

fun main() {
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).random() == 3 // .shuffled().last() == 3
    if (isJugglingProficient) {
        swordsJuggling = (2..5).random()
    }


    /* ловим ошибку с помощью try, блоков catch может быть несколько
    * тем самым программа не упадёт, а просто выведет сообщение */
    try {
        //    swordsJuggling ?: throw UnskilledSwordJugglerException() /* хуже читаемость,
        //    подходит только для простых случаев */
        proficiencyCheck(swordsJuggling)

        println(swordsJuggling)

        swordsJuggling = swordsJuggling!!.plus(1) /* '!!' оператор позволит продолжить выполнение кода
        даже при null значении, но выдаст ошибку*/
    } catch (e: Exception) {
        println(e)
    } finally {
        // finally необязателен к написанию, но код внутри выполнится вне зависимости была ошибка или нет
    }

    println("You juggle $swordsJuggling swords!")
}

fun proficiencyCheck(swordsJuggling: Int?) {
//    swordsJuggling
//        ?: throw UnskilledSwordJugglerException()
    // checkNotNull проверяет на null и если false то возвращает значение
    checkNotNull(swordsJuggling, { "Player cannot juggle swords" })
}

// Пользовательское исключение
class UnskilledSwordJugglerException() :
    IllegalStateException("Player cannot juggle swords")
