package dev.rfj.steps

import dev.rfj.canvas.Canvas
import dev.rfj.domain.tuple.Color
import dev.rfj.steps.canvas.CanvasMap
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals
import kotlin.test.fail


class CanvasSteps(
        private val canvasMap: CanvasMap
) {

    @Given("{name} ← canvas\\({int}, {int})")
    fun cCanvas(name: String, width: Int, height: Int) {
        val canvas = Canvas(width, height)
        canvasMap[name] = canvas
    }

    @Then("{name}.width = {int}")
    fun cWidth(name: String, expectedWidth: Int) {
        val canvas = canvasMap[name] ?: fail("Canvas not found!")
        assertEquals(expectedWidth, canvas.width)
    }

    @Then("{name}.height = {int}")
    fun cHeight(name: String, expectedHeight: Int) {
        val canvas = canvasMap[name] ?: fail("Canvas not found!")
        assertEquals(expectedHeight, canvas.height)
    }

    @Then("every pixel of {name} is {color}")
    fun everyPixelOfCIsColor(canvasName: String, color: Color) {
        val canvas = canvasMap[canvasName] ?: fail("Canvas with name $canvasName not found!")
        canvas.eachPixel {
            assertEquals(color, it)
        }
    }

    @When("{canvasName}.write_pixel\\({int}, {int}, {colorName})")
    fun writePixelAt(canvas: Canvas, x: Int, y: Int, color: Color) {
        canvas.setPixelAt(x, y, color)
    }

    @Then("{canvasName}.pixel_at\\({int}, {int}) = {colorName}")
    fun readPixelAt(canvas: Canvas, x: Int, y: Int, expected: Color) {
        assertEquals(expected, canvas.getPixelAt(x, y))
    }
}