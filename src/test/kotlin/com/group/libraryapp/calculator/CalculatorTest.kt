package com.group.libraryapp.calculator

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test


class CalculatorTest {

    @Test
    fun addTest() {

        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)

        //then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun minusTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.minus(3)

        //then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun multiplyTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.multiply(3)

        //then
        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun divideTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.divide(2)

        //then
        assertThat(calculator.number).isEqualTo(2);
    }

    @Test
    fun divideExceptionTest() {
        //given
        val calculator = Calculator(5)

        //when
        var message1 = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message
        assertThat(message1).isEqualTo("0으로 나눌 수 없습니다.")

        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
        }

    }
}