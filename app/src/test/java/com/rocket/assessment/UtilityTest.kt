package com.rocket.assessment

import com.rocket.assessment.utility.getDateOnly
import org.junit.Assert
import org.junit.Test

/**
 * Tests utility functions
 */
class UtilityTest {
    @Test
    fun getDateOnlyTest() {
        val input1 = "2018-07-25 06:22:22"
        val output1 = "2018-07-25"
        val input2 = "2018-07-25"
        val output2 = "2018-07-25"
        val input3 = ""
        val output3 = ""

        Assert.assertEquals(input1.getDateOnly(), output1)
        Assert.assertEquals(input2.getDateOnly(), output2)
        Assert.assertEquals(input3.getDateOnly(), output3)
    }
}