package com.cavigna.talentodigital


import com.cavigna.talentodigital.utils.parseDate
import com.cavigna.talentodigital.utils.precioUSD
import com.cavigna.talentodigital.utils.redondeo
import org.junit.Assert.*
import org.junit.Test

class UnitTestApp {

    @Test
    fun `testPrice`(){
        val actual  = precioUSD(8990)
        val expected = "Precio: USD 8990,00"
        assertEquals(actual,expected )
    }
//
//    @Test
//    fun`parseDateTest`(){
//        assertEquals(parseDate("2021-05-08T00:00:00Z"),"2021-05-08T00:00:00Z" )
//    }

    @Test
    fun`testRedeondeo`(){
        val actual  = redondeo("8990")
        val expected = "8990,00"

        assertEquals(actual, expected)

    }
}