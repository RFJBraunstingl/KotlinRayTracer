package dev.rfj.paramtypes

import dev.rfj.canvas.Canvas
import dev.rfj.domain.*
import dev.rfj.domain.shapes.Sphere
import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.store.TupleStore
import dev.rfj.domain.tuple.Color
import dev.rfj.matrix.Matrix
import dev.rfj.steps.canvas.CanvasMap
import io.cucumber.java.ParameterType
import kotlin.test.fail

class CustomGherkinTypes(
        private val tupleStore: TupleStore,
        private val canvasMap: CanvasMap,
        private val matrixMap: MatrixMap,
        private val rayStore: RayMap,
        private val sphereMap: SphereMap,
        private val intersectionMap: IntersectionMap,
        private val intersectionCollectionStore: IntersectionCollectionStore
) {

    @ParameterType("([a-zA-Z0-9_]+)")
    fun name(name: String): String = name

    @ParameterType("([a-z0-9]+)")
    fun tupleName(name: String): Tuple = tupleStore.findByName(name)

    @ParameterType("([a-zA-Z0-9]+)")
    fun colorName(name: String): Color {
        val color = tupleStore.findByName(name)
        if (color !is Color) fail("$name was referenced as Color but is not a color")
        return color
    }

    @ParameterType("([a-zA-Z0-9]+)")
    fun canvasName(name: String): Canvas {
        return canvasMap[name] ?: fail("Could not find Canvas with name $name!")
    }

    // matrices must be named with capital letters
    @ParameterType("([A-Z0-9_]+|(identity_matrix))")
    fun matrixName(name: String): Matrix {
        if ("identity_matrix" == name)
            return Matrix.identity4x4()

        return matrixMap[name] ?: fail("Could not find matrix with name $name!")
    }

    // rays start with "r"
    @ParameterType("r[a-z0-9]*")
    fun rayName(name: String): Ray {
        return rayStore[name] ?: fail("Could not find ray with name $name")
    }

    @ParameterType("[a-z0-9]+")
    fun sphereName(name: String): Sphere {
        return sphereMap[name] ?: fail("Could not find sphere w/ that name :( ($name)")
    }

    @ParameterType("[a-z0-9]+")
    fun intersectionName(name: String) = intersectionMap[name] ?: fail("Error searching intersection store for $name")

    @ParameterType("[a-z0-9]+")
    fun intersectionCollectionName(name: String) = intersectionCollectionStore[name] ?: fail("Intersection collection not found!")

    @ParameterType("tuple\\((-?\\d+\\.\\d+), (-?\\d+\\.\\d+), (-?\\d+\\.\\d+), (-?\\d+\\.\\d+)\\)")
    fun tuple(x: String, y: String, z: String, w: String): Tuple {
        return Tuple.create(
                x.toDouble(),
                y.toDouble(),
                z.toDouble(),
                w.toDouble()
        )
    }

    @ParameterType("vector\\((-?\\d+\\.?\\d*), (-?\\d+\\.?\\d*), (-?\\d+\\.?\\d*)\\)")
    fun vector(x: String, y: String, z: String): Tuple {
        return Tuple.vector(
                x.toDouble(),
                y.toDouble(),
                z.toDouble()
        )
    }

    @ParameterType("point\\((-?\\d+\\.?\\d*), (-?\\d+\\.?\\d*), (-?\\d+\\.?\\d*)\\)")
    fun point(x: String, y: String, z: String): Tuple {
        return Tuple.point(
                x.toDouble(),
                y.toDouble(),
                z.toDouble()
        )
    }

    @ParameterType("color\\((-?\\d+\\.\\d+), (-?\\d+\\.\\d+), (-?\\d+\\.\\d+)\\)")
    fun color(red: String, green: String, blue: String): Color {
        return Tuple.color(
                red.toDouble(),
                green.toDouble(),
                blue.toDouble()
        )
    }

    @ParameterType("(-?[0-9]+)\\/(-?[0-9]+)")
    fun intDivision(op1: String, op2: String): Double {
        return op1.toDouble() / op2.toDouble()
    }
}