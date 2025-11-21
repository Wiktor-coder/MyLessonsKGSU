package functions

import java.io.File

fun main() {

    /*
     Функция apply. Ее можно считать функцией настройки: она позволяет вызвать несколько функций
    для объекта-получателя и настроить его для дальнейшего использования.
    После выполнения указанного лямбда-выражения apply возвращает настроенный объект-получатель.
    Функцию apply можно использовать, чтобы уменьшить количество повторений при подготовке
    объекта к использованию.
    */
    val file = File("menu-file.txt").apply {
        setReadable(true) // На деле, file.setReadable(true)
        setWritable(true) // На деле, file.setWritable(true)
        setExecutable(false) // На деле, file.setExecutable(false)
    }

    /*
     Функция .let, которая определяет переменную в область видимости заданной лямбды и
    позволяет использовать ключевое слово it для ссылки на нее. Вот пример let,
    который возводит в квадрат первое число в списке:
    */
    val firstItemSquared = listOf(1, 2, 3, 4, 5).first().let {
        it * it
    }

    /*
    Изменения приветствия при помощи .let, используем безопасный вызов "?" и оператор Элвис "?:"
    */
    fun hello(name: String?): String = name?.let { "Hello, $it" } ?: "Hello gays"
    /*
    Несколько различий между let и apply, о которых стоит упомянуть: как вы уже видели,
    let передает объект-приемник в лямбду, но apply ничего не передает.
    Также apply возвращает текущий объект-приемник, как только анонимная функция завершает работу.
    Функция let, напротив, возвращает результат выполнения последней строки лямбды
    */

    /*
     Функция run похожа на apply, точно так же ограничивая относительную область видимости,
     но не возвращает объект-приемник. Когда требуется вызвать несколько функций:
     вызов цепочкой с помощью run проще читать и анализировать. "::" это ссылка на функцию.
    */
    "Polarises, Supreme Master of NyetHack"
        .run(::nameIsLong)
        .run(::playerCreateMessage)
        .run(::println)
    //  Сравните цепочку вызовов в run с тремя вложенными вызовами функций:
    println(playerCreateMessage(nameIsLong("Polarises, Supreme Master of NyetHack")))
    // Другая форма вызова run, без объекта-приемника.
    val point = 100
    val status = run {
        if (point == 100) "perfect" else "injuries"
    }

    /*
     Функция with - это разновидность run. Она ведет себя похожим образом,
     но использует другие соглашения вызова. Функция with требует, чтобы объект-приемник
     передавался ей в первом аргументе, а не как субъект вызова, как это принято
     в других стандартных функциях:
    */
    val nameTooLOng = with("Polarises, Supreme Master of NyetHack") {
        length >= 15
    }
}

private fun nameIsLong(name: String): Boolean = name.length >= 15
private fun playerCreateMessage(nameTooLong: Boolean): String {
    return if (nameTooLong) {
        "good"
    } else {
        "Not Good"
    }
}