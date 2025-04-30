package racingcar

import camp.nextstep.edu.missionutils.Console

class Racing {
    private val cars = arrayListOf<Car>()
    private val tryCount: Int
    private val winners = arrayListOf<Car>()

    init {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        Console.readLine()
            .split(",")
            .forEach { addCar(it) }

        println("시도할 횟수는 몇 회인가요?")
        tryCount = Console.readLine()?.toIntOrNull()
            ?: throw IllegalArgumentException("시도 횟수를 입력해주세요")
        require(tryCount > 0) {"시도 횟수는 1 이상이어야 합니다"}
    }

    private fun addCar(name: String) {
        val newCar = Car(name)
        require(cars.indexOf(newCar) == -1) {"자동차 이름이 중복됩니다"}
        cars.add(newCar)
    }

    fun start() {
        require(cars.size > 1) {"혼자만의 레이스는 외롭잖아요"}

        for (i in 0 until tryCount) {
            moveCars()
            println()
        }

        calculateWinners()

        println("최종 우승자 : ${winners.joinToString(", ") { it.name }}")
    }

    private fun moveCars() {
        cars.forEach {
            it.moveRandomly()
            println("${it.name} : ${"-".repeat(it.position)}")
        }
    }

    private fun calculateWinners() {
        val winnerPosition = cars.maxOf { it.position }
        winners += cars.filter { it.position == winnerPosition }
    }
}