package racingcar

import camp.nextstep.edu.missionutils.Console

class Racing {
    private val cars = arrayListOf<Car>()
    private var tryCount: Int
    private val winners = arrayListOf<Car>()

    init {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        Console.readLine()
            .split(",")
            .forEach { name ->
                if (name.isEmpty() || name.length > 5) {
                    throw IllegalArgumentException("자동차 이름을 5자 이하로 입력해주세요")
                }
                cars.add(Car(name))
            }

        println("시도할 횟수는 몇 회인가요?")
        tryCount = Console.readLine()
            ?.toIntOrNull()
            ?: throw IllegalArgumentException("시도할 횟수를 숫자로 입력하세요")
    }

    fun start() {
        for (i in 0 until tryCount) {
            moveCars()
            println()
        }

        calculateWinners()

        println("최종 우승자: ${winners.joinToString(", ") { it.name }}")
    }

    private fun moveCars() {
        cars.forEach {
            it.moveRandomly()
            println("${it.name} : ${it.position}")
        }
    }

    private fun calculateWinners() {
        val winnerPosition = cars.maxOf { it.position }
        winners += cars.filter { it.position == winnerPosition }
    }
}