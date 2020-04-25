package dev.rfj.steps

import dev.rfj.canvas.Canvas
import dev.rfj.domain.tuple.Color
import dev.rfj.output.PPM
import dev.rfj.steps.canvas.CanvasMap
import io.cucumber.java.PendingException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals
import kotlin.test.fail

class CanvasSteps(
        private val canvasMap: CanvasMap
) {

    private lateinit var ppm: PPM

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

    @When("every pixel of {canvasName} is set to {color}")
    fun everyPixelOfCIsSetToColor(canvas: Canvas, color: Color) {
        for (x in 0 until canvas.width)
            for (y in 0 until canvas.height)
                canvas.setPixelAt(x, y, color)
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

    @When("ppm ← canvas_to_ppm\\({canvasName})")
    fun canvasToPPM(canvas: Canvas) {
        ppm = canvas.toPPM()
    }

    @Then("lines {int}-{int} of ppm are")
    fun linesOfPpmAre(yStart: Int, yEnd: Int, docString: String) {
        var docStringIndex = 0
        for (i in yStart..yEnd)
            assertEquals(docString.lines()[docStringIndex++], ppm.lines()[i - 1])
    }

    @Then("ppm ends with a newline character")
    fun ppmEndsWithANewlineCharacter() {
        assertEquals("", ppm.lines().last())
    }
}