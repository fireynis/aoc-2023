fun main() {

    val numStrings = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            var foundCount = 0
            var valString = ""
            var valHolder = ""
            line.forEach { char ->
                if (char.isDigit()) {
                    if (foundCount == 0) {
                        valString += char.toString()
                    }
                    valHolder = char.toString()
                    foundCount++
                }
            }
            valString += valHolder
            sum += valString.toInt()
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val regex = Regex("""\d|\D+""")
        for (line in input) {
            var valString = ""
            var valHolder = ""
            var foundFirst = false
            val splits = regex.findAll(line).map { it.groupValues.first() }.toList()
            for (split in splits) {
                if (split.length == 1 && split[0].isDigit()) {
                    if (!foundFirst) {
                        valString += split
                        foundFirst = true
                    }
                    valHolder = split
                } else if (split.length < 3) {
                    continue
                }
                var lowestIndex = split.length
                var lowestString = ""
                var highestIndex = 0
                var highestString = ""
                var foundAnything = false
                for (numString in numStrings) {
                    var index = split.indexOf(numString.key)
                    while (index != -1) {
                        foundAnything = true
                        if (index <= lowestIndex ) {
                            lowestIndex = index
                            lowestString = numString.value
                        }
                        if (index >= highestIndex) {
                            highestIndex = index
                            highestString = numString.value
                        }
                        index = split.indexOf(numString.key, index + 1)
                    }
                }
                if (!foundFirst && foundAnything) {
                    valString = lowestString
                    foundFirst = true
                }
                if (foundAnything) {
                    valHolder = highestString
                }

            }
            valString += valHolder
            sum += valString.toInt()
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)
    val testInput2 = readInput("Day01_part2_test")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
