import kotlin.math.max
import kotlin.math.min

fun main() {

    fun checkChar(testLine: String, i: Int): Boolean {
        return testLine[i] != '.' && !testLine[i].isDigit()
    }

    fun checkLine(startIndex: Int, endIndex: Int, testLine: String): Boolean {
        val start = max(startIndex - 1, 0)
        val end = min(endIndex + 1, testLine.length - 1)
        for (i in start..end) {
            if (checkChar(testLine, i)) return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for ((lineIndex, line) in input.withIndex()) {
            var numberString = ""
            var inNumber = false
            var startIndex = 0
            var endIndex: Int
            for ((index, char) in line.withIndex()) {
                if (char.isDigit()) {
                    if (!inNumber) {
                        startIndex = index
                        inNumber = true
                    }
                    numberString += char
                } else if (numberString == "") {
                    continue
                } else {
                    endIndex = max(index - 1, 0)
                    val number = numberString.toInt()
                    numberString = ""
                    inNumber = false
                    var foundSymbol = false
                    /*
                    Need to check the array index before the start index (if not zero)
                    Need to check the array index after the end index (if not end of string)
                    Need to check the string before and after from startIndex-1 to endIndex+1
                     */
                    if (startIndex > 0) {
                        if (checkChar(line, startIndex - 1)) {
                            foundSymbol = true
                        }
                    }
                    if (endIndex < line.length - 1) {
                        if (checkChar(line, endIndex + 1)) {
                            foundSymbol = true
                        }
                    }
                    if (lineIndex > 0) {
                        val testLine = input[lineIndex - 1]
                        if (checkLine(startIndex, endIndex, testLine)) {
                            foundSymbol = true
                        }
                    }
                    if (lineIndex < input.size - 1) {
                        val testLine = input[lineIndex + 1]
                        if (checkLine(startIndex, endIndex, testLine)) {
                            foundSymbol = true
                        }
                    }
                    if (foundSymbol) {
                        sum += number
                    }
                }
                if (index == line.length - 1 && numberString != "") {
                    endIndex = max(index - 1, 0)
                    val number = numberString.toInt()
                    numberString = ""
                    inNumber = false
                    var foundSymbol = false
                    /*
                    Need to check the array index before the start index (if not zero)
                    Need to check the array index after the end index (if not end of string)
                    Need to check the string before and after from startIndex-1 to endIndex+1
                     */
                    if (startIndex > 0) {
                        if (checkChar(line, startIndex - 1)) {
                            foundSymbol = true
                        }
                    }
                    if (endIndex < line.length - 1) {
                        if (checkChar(line, endIndex + 1)) {
                            foundSymbol = true
                        }
                    }
                    if (lineIndex > 0) {
                        val testLine = input[lineIndex - 1]
                        if (checkLine(startIndex, endIndex, testLine)) {
                            foundSymbol = true
                        }
                    }
                    if (lineIndex < input.size - 1) {
                        val testLine = input[lineIndex + 1]
                        if (checkLine(startIndex, endIndex, testLine)) {
                            foundSymbol = true
                        }
                    }
                    if (foundSymbol) {
                        sum += number
                    }
                }
            }
        }
        return sum
    }

    fun checkLineInBounds(line: String, bound: Int): MutableList<Int> {
        var numberString = ""
        var startIndex = 0
        var endIndex = 0
        val numList = mutableListOf<Int>()
        for ((index, char) in line.withIndex()) {
            if (char.isDigit()) {
                if (numberString == "") {
                    startIndex = index
                }
                endIndex = index
                numberString += char
                continue
            }
            if (numberString == "") {
                continue
            }
            startIndex = max(startIndex-1, 0)
            if (bound in startIndex..index) {
                val number = numberString.toInt()
                numList.add(number)
            }
            numberString = ""
        }
        if (numberString != "") {
            if (bound in startIndex..endIndex) {
                val number = numberString.toInt()
                numList.add(number)
            }
        }
        return numList
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for ((lineIndex, line) in input.withIndex()) {
            for ((index, char) in line.withIndex()) {
                if (char == '*') {
                    val listNums = checkLineInBounds(line, index)
                    if (lineIndex > 0) {
                        listNums.addAll(checkLineInBounds(input[lineIndex-1], index))
                    }
                    if (lineIndex+1 <= input.size-1) {
                        listNums.addAll(checkLineInBounds(input[lineIndex+1], index))
                    }
                    if (listNums.size == 2) {
                        sum += listNums[0]*listNums[1]
                    }
                }
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 925)
    check(part2(testInput) == 6756)

    val input = readInput("Day03")
    part1(input).println()
    // 93994191
    part2(input).println()
}
