fun main() {

    val maxRed = 12
    val maxGreen = 13
    val maxBlue = 14

    fun determineValidGame(line: String): Int {
        val gameRegex = Regex("^Game\\s(\\d+):(.*)\$")
        val gameMatch = gameRegex.matchEntire(line)
        if (gameMatch != null) {
            val gameData = gameMatch.groupValues[2].split(";")
            for (game in gameData) {
                val cleanSets = game.split(",")
                for (cleanSet in cleanSets) {
                    val trimSets = cleanSet.trim()
                    val cleanSetValues = trimSets.split(" ")
                    when (cleanSetValues[1]) {
                        "red" -> {
                            if (cleanSetValues[0].toInt() > maxRed) {
                                return 0
                            }
                        }

                        "green" -> {
                            if (cleanSetValues[0].toInt() > maxGreen) {
                                return 0
                            }
                        }

                        "blue" -> {
                            if (cleanSetValues[0].toInt() > maxBlue) {
                                return 0
                            }
                        }
                    }
                }
            }
            return gameMatch.groupValues[1].toInt()
        }
        return 0
    }

    fun determineGamePower(line: String): Int {
        var localMaxRed = 0
        var localMaxGreen = 0
        var localMaxBlue = 0
        val gameRegex = Regex("^Game\\s(\\d+):(.*)\$")
        val gameMatch = gameRegex.matchEntire(line)
        if (gameMatch != null) {
            val gameData = gameMatch.groupValues[2].split(";")
            for (game in gameData) {
                val cleanSets = game.split(",")
                for (cleanSet in cleanSets) {
                    val trimSets = cleanSet.trim()
                    val cleanSetValues = trimSets.split(" ")
                    when (cleanSetValues[1]) {
                        "red" -> {
                            if (cleanSetValues[0].toInt() > localMaxRed) {
                                localMaxRed = cleanSetValues[0].toInt()
                            }
                        }

                        "green" -> {
                            if (cleanSetValues[0].toInt() > localMaxGreen) {
                                localMaxGreen = cleanSetValues[0].toInt()
                            }
                        }

                        "blue" -> {
                            if (cleanSetValues[0].toInt() > localMaxBlue) {
                                localMaxBlue = cleanSetValues[0].toInt()
                            }
                        }
                    }
                }
            }
        }
        return localMaxBlue * localMaxGreen * localMaxRed
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            sum += determineValidGame(line)
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            sum += determineGamePower(line)
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    val testInput2 = readInput("Day02_part2_test")
    check(part2(testInput2) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
