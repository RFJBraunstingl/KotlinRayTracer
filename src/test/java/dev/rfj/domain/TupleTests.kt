package dev.rfj.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TupleTests {

    @Test
    fun testTupleCreation() {
        assertEquals(Tuple.ZERO, Tuple.create(0.0, 0.0, 0.0, 0.0))
    }

    @Test
    fun testEquality() {
        val aTuple = Tuple.create(1.0, 2.0, 3.0, 0.0)
        val theSameTuple = Tuple.create(1.0, 2.0, 3.0, 0.0)
        val aDifferentTuple = Tuple.create(3.0, 2.0, 1.0, 0.0)
        assertEquals(aTuple, aTuple)
        assertEquals(aTuple, theSameTuple)
        assertNotEquals(aTuple, aDifferentTuple)
    }

    @Test
    fun testVectorCreation() {
        val vector = Tuple.createVector(1.0, 2.0, 3.0)
        assertEquals(Tuple.create(1.0, 2.0, 3.0, 0.0), vector)
        assertTrue { vector.isVector() }
        assertFalse { vector.isPoint() }
    }

    @Test
    fun testPointCreation() {
        val point = Tuple.createPoint(1.0, 2.0, 3.0)
        assertEquals(Tuple.create(1.0, 2.0, 3.0, 1.0), point)
        assertTrue { point.isPoint() }
        assertFalse { point.isVector() }
    }

    @Test
    fun testAddition() {
        val tuple1 = Tuple.create(1.0, 2.0, 3.0, 0.0)
        val tuple2 = Tuple.create(2.0, 3.0, 4.0, 1.0)
        val expect = Tuple.create(3.0, 5.0, 7.0, 1.0)
        assertEquals(expect, tuple1.plus(tuple2))
    }

    @Test
    fun testSubstraction() {
        val tuple1 = Tuple.create(1.0, 3.0, 4.0, 1.0)
        val tuple2 = Tuple.create(2.0, 3.0, 3.0, 1.0)
        val expect = Tuple.create(-1.0,0.0, 1.0, 0.0)
        assertEquals(expect, tuple1.minus(tuple2))
    }
}