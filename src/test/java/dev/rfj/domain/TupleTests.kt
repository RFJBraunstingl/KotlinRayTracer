package dev.rfj.domain

import org.junit.jupiter.api.Test
import kotlin.math.sqrt
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TupleTests {

    @Test
    fun testTupleCreation() {
        assertEquals(Tuple.ZERO_VECTOR, Tuple.create(0.0, 0.0, 0.0, 0.0))
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

    @Test
    fun testTupleNegation() {
        val aTuple = Tuple.create(1.0, -2.0, 3.0, -4.0)
        val expect = Tuple.create(-1.0, 2.0, -3.0, 4.0)
        assertEquals(expect, -aTuple)
    }

    @Test
    fun testScalarMultiplication() {
        val aTuple = Tuple.create(1.0, -2.0, 3.0, -4.0)
        val aScalar = 3.5
        val expect = Tuple.create(3.5, -7.0, 10.5, -14.0)
        assertEquals(expect, aTuple.multipliedBy(aScalar))
    }

    @Test
    fun testScalarDivision() {
        val aTuple = Tuple.create(1.0, -2.0, 3.0, -4.0)
        val halfed = Tuple.create(0.5, -1.0, 1.5, -2.0)

        assertEquals(halfed, aTuple.dividedBy(2.0))
    }

    @Test
    fun testUnitVectorMagnitude() {
        val unitVectorMagnitude = 1.0

        assertEquals(unitVectorMagnitude, Tuple.createVector(1.0, 0.0, 0.0).magnitude())
        assertEquals(unitVectorMagnitude, Tuple.createVector(0.0, 1.0, 0.0).magnitude())
        assertEquals(unitVectorMagnitude, Tuple.createVector(0.0, 0.0, 1.0).magnitude())
    }

    @Test
    fun testMagnitudeCalculation() {
        val vector1 = Tuple.createVector(1.0, 2.0, 3.0)
        val vector2 = Tuple.createVector(-1.0, -2.0, -3.0)
        assertEquals(sqrt(14.0), vector1.magnitude())
        assertEquals(sqrt(14.0), vector2.magnitude())
    }
}