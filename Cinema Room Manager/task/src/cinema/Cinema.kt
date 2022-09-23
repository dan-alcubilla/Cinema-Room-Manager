package cinema

fun calculatePrice(rows: Int, seats: Int, rowNumber: MutableList<Int>) {
    if (rows * seats <= 60) {
        println()
        println("Ticket price: \$10")
        println()
    } else {
        if (rowNumber.last() <= 4) {
            println()
            println("Ticket price: \$10")
            println()
        } else {
            println()
            println("Ticket price: \$8")
            println()
        }
    }
}

fun MutableList<MutableList<Char>>.chosenSeat(
    rowNumber: MutableList<Int>,
    seatNumber: MutableList<Int>
): MutableList<MutableList<Char>> {
    for (i in 0 until rowNumber.size) {
        this[rowNumber[i] - 1][seatNumber[i] - 1] = 'B'
    }
    return this
}

fun showScreenRoom(screenRoom: MutableList<MutableList<Char>>, seats: Int) {
    println()
    println("Cinema:")
    val row1 = mutableListOf<String>()
    for (x in 1..seats) row1.add(x.toString())
    println("  " + row1.joinToString(" "))
    for (i in screenRoom.indices) {
        println("${i+1} ${screenRoom[i].joinToString(" ")}")
    }
    println()
}

fun createScreenRoom(rows: Int, seats: Int): MutableList<MutableList<Char>> {
    val screenRoom = mutableListOf<MutableList<Char>>()
    for (i in 0 until rows) {
        val row = mutableListOf<Char>()
        for (j in 0 until seats) {
            row.add('S')
        }
        screenRoom.add(row)
    }
    return screenRoom
}

fun main() {
    var exit = false
    var option: Int
    var ticketBought = false
    var purchasedTickets = 0
    var currentIncome = 0

    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    println()
    val rowNumber = MutableList(0) { 0 }
    val seatNumber = MutableList(0) { 0 }

    while (!exit) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        option = readln().toInt()

        when (option) {
            1 -> {
                if (ticketBought) {
                    showScreenRoom(createScreenRoom(rows, seats).chosenSeat(rowNumber, seatNumber), seats)
                } else {
                    showScreenRoom(createScreenRoom(rows, seats), seats)
                }
            }
            2 -> {
                println()
                var wrongInput = false
                var auxRow: Int
                var auxSeat: Int

                while (!wrongInput) {
                    println("Enter a row number:")
                    auxRow = readln().toInt()
                    println("Enter a seat number in that row:")
                    auxSeat = readln().toInt()
                    if (auxRow in rowNumber && auxSeat in seatNumber && rowNumber.indexOf(auxRow) == seatNumber.indexOf(auxSeat)) {
                        println()
                        println("That ticket has already been purchased!")
                        println()
                    } else if (auxRow > rows || auxSeat > seats) {
                        println()
                        println("Wrong input!")
                        println()
                    } else {
                        //println("Enter a row number:")
                        rowNumber.add(auxRow)
                        //println("Enter a seat number in that row:")
                        seatNumber.add(auxSeat)
                        calculatePrice(rows, seats, rowNumber)

                        ticketBought = true
                        purchasedTickets++
                        wrongInput = true
                    }
                    currentIncome += if (rows * seats <= 60) {
                        10
                    } else {
                        if (rowNumber.last() <= 4) {
                            10
                        } else {
                            8
                        }
                    }
                }
            }
            3 -> {
                println()
                println("Number of purchased tickets: $purchasedTickets")
                val percentage = purchasedTickets.toFloat() * 100 / (rows * seats)
                println("Percentage: ${String.format("%.2f", percentage)}%")
                println("Current income: $$currentIncome")
                println("Total income: $${if (rows * seats <= 60) {
                    rows * seats * 10
                } else {
                    if (rows % 2 == 0) {
                        (rows / 2 * seats) * 10 + (rows / 2 * seats) * 8
                    } else {
                        (rows / 2 * seats) * 10 + ((rows - rows / 2) * seats) * 8
                    }
                }}")
                println()
            }
            0 -> exit = true
        }
    }
}