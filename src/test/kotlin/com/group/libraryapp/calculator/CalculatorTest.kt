package com.group.libraryapp.calculator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class CalculatorTest {

    @Test
    fun addTest() {

        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)

        //then
        if (calculator.number != 8) {
            throw IllegalStateException()
        }
    }

    @Test
    fun minusTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.minus(3)

        //then
        if (calculator.number != 2) {
            throw IllegalStateException()
        }
    }

}