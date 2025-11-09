package variable

fun main() {
//    val a = "Missisipi".count { it ->
//        it == 'i'
//    }
//    println(a)
//    val b = "Mississippi".contains ('s', true)
//    val d = "Mississippi".any { it ->
//        it == 's'
//    }
//    println("$b $d")

    a(listOf(1,2,3,4,5)) {
        println(it * 2)
    }
}

fun <T> a (
    items: List<T>,
    processor: (T) -> Unit,
) {
    items.forEach(processor)
}