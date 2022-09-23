fun main() {
    // Do not touch code below
    var inputList: MutableList<MutableList<String>> = mutableListOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toMutableList()
        inputList.add(strings)
    }
    // write your code here
    val outputList: MutableList<MutableList<String>> = mutableListOf()
    outputList.add(inputList.last())
    outputList.add(inputList.first())
    println(outputList)
}