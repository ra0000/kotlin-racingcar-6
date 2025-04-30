package racingcar

import camp.nextstep.edu.missionutils.Randoms

class Car(val name: String) {
    var position: Int = 0

    init {
        require(name.isNotBlank()) {"자동차 이름을 비어있지 않게 입력해주세요"}
        require(name.length <= 5) {"자동차 이름을 5자 이하로 입력해주세요"}
    }

    fun moveRandomly() {
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            position++
        }
    }

    override fun equals(other: Any?): Boolean {
        return name === (other as? Car)?.name
    }
}