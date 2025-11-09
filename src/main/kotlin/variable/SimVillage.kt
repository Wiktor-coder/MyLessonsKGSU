package variable

fun main() {

    // Лямбда функция анонимная
    println(
        "print print: ${
            {
                val currentYear = 2021
                "Welcome to SimVillage, Mayor! (copyright $currentYear)"
            }()
        }"
    )

    // присвоение в переменную (" :() -> String = ") эквивалентно (" (): String = ") для fun
    val greetingFunction: () -> String = {
        val currentYear = 2025
        "Welcome to SimVillage, Mayor! (copyright $currentYear)"
    }
    println("print val: ${greetingFunction()}")

    // присвоение в переменную принимающая аргумент строку
    val greetingFunction1: (String) -> String = { playerName ->
        val currentYear = 2025
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
    println("print val 1: ${greetingFunction1("Wictor")}")

    // используем ключевое имя $it, но только если принимаем один аргумент
    val greetingFunction2: (String) -> String = {
        val currentYear = 2025
        "Welcome to SimVillage, $it! (copyright $currentYear)"
    }
    println("print val 2: ${greetingFunction2("Wictor")}")

    // принимаем доп аргумент
    val greetingFunction3: (String, Int) -> String = { playerName, numBuildings ->
        val currentYear = 2025
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
    println("print val 3: ${greetingFunction3("Wictor", 21)}")

    // указываем тип (тип это: String, Int) параметра в лямбде избавляясь от конструктора
    val greetingFunction4 = { playerName: String, numBuildings: Int ->
        val currentYear = 2025
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
    println("print val 4: ${greetingFunction4("Wictor", 21)}")

    // объявление функции
    fun greetingFunction1(): String {
        val currentYear = 2025
        return "Welcome to SimVillage, Mayor! (copyright $currentYear)"
    }
    println("print fun: ${greetingFunction1()}")

    /* fun которая принимает строку и другую fun в качестве параметров,
    генерирует случайное число и передаёт в принимаемую fun
    принятую стоку и сгенерированное число
     */
    //runSimulation("Elissa",greetingFunction4) //после изменений стала неактуальна

    /*
    передаём как аргумента в качестве параметра ссылку на fun
    два варианта вызова одной и той-же fun
     */
    runSimulation("Elissa 1", ::printConstructionCost, greetingFunction4)
    /* если лямбда передаётся последним аргументом, то её можно вынести за скобки ()
    и создать кастомную логику
     */
    runSimulation("Elissa 2", ::printConstructionCost) { playerName, numBuildings ->
        val currentYear = 2025
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }

    runSimulation1()
}

/*
вызывая fun так configureGreetingFunction()
выполняется только её первая часть это
val structureType = "hospital"
var numBuildings = (1..10).random()
Промежуточный результат: У нас есть функция типа (String) -> String
(...)("P")
Визуализация процесса:
configureGreetingFunction()("P")
         ↓
[ВЫЗОВ configureGreetingFunction] → возвращает функцию
         ↓
[ВЫЗОВ возвращенной функции с "P"] → возвращает строку
         ↓
"Welcome to SimVillage, P! (copyright 2025)"
 */
fun runSimulation1() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("Loli"))
    println(configureGreetingFunction()("P"))
}

fun configureGreetingFunction(): (String) -> String {
    val structureType = "hospital"
    var numBuildings = (1..10).random()
    return { playerName: String ->
        val currentYear = 2025
        numBuildings += 1
        println("Adding $numBuildings $structureType")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
}

/* fun которая принимает строку и другую fun в качестве параметров,
генерирует случайное число и передаёт в принимаемую fun
принятую стоку и сгенерированное число
inline в данном случае упрощает работу JVM не создаётся доп объект
тем самым затрачивается меньше памяти
 */
inline fun runSimulation(
    playerName: String,
    costPrinter: (Int) -> Unit,
    greetingFunction: (String, Int) -> String
) {
    /* .shuffled() создаёт и перетасовывает список .last() берёт последний элемент списка
    используем когда нужен перемешанный список или несколько элементов из одного списка
    при большом списке медленный и занимает много памяти
     */
    val numBuildings = (1..5).shuffled().last() // случайно выберет 1,2,3,4 или 5
    costPrinter(numBuildings)
    /*
    создаёт случайное число, быстро и не занимает много памяти и быстро работает
    используем когда нужно одно случайное значение
     */
    val num2 = (1..10).random()
    val str = ('a'..'z').random()
    println("$num2: $str: ${greetingFunction(playerName, numBuildings)}")
}

fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("construction cost ${cost * numBuildings}")
}