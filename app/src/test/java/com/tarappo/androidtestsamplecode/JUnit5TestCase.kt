package com.tarappo.androidtestsamplecode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JUnit5TestCase {
    @Test
    @DisplayName("テストケース名")
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
