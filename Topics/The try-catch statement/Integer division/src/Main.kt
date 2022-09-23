fun intDivision(x: String, y: String): Int {
    try {
        val dividend = x.toInt()
        val divider = y.toInt()
        return dividend / divider
    } catch (e: ArithmeticException) {
        println("Exception: division by zero!")
    } catch (e: NumberFormatException) {
        println("Read values are not integers.")
    }
    return 0
}

fun main() {
    val x = readln()
    val y = readln()
    println(intDivision(x, y))
}