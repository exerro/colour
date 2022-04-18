package me.exerro

import me.exerro.colour.Colour
import me.exerro.colour.fromHex
import org.lwjgl.glfw.GLFW
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL46C
import org.lwjgl.system.MemoryUtil.NULL

fun rect(
    context: Long,
    colour: Colour,
    x: Float,
    y: Float,
    width: Float,
    height: Float,
) {
    val c = NVGColor.calloc()
    NanoVG.nvgRGBAf(colour.red, colour.green, colour.blue, colour.alpha, c)
    NanoVG.nvgBeginPath(context)
    NanoVG.nvgRect(context, x, y, width, height)
    NanoVG.nvgClosePath(context)
    NanoVG.nvgFillColor(context, c)
    NanoVG.nvgFill(context)
    c.free()
}

fun drawWindow(context: Long) {
    val colours = listOf(
        "#efe8c5",
        "#6e362f",
        "#eedc8e",
        "#552a3f",
        "#d6b858",
        "#d37dad",
    )

    for ((i, v) in colours.withIndex()) {
        rect(context, Colour.fromHex(v), 64f * i, 0f, 64f, 32f)
    }
}

fun main() {
    GLFW.glfwInit()

    val window = GLFW.glfwCreateWindow(1080, 720, "Colour test", NULL, NULL)
    assert(window != NULL)

    GLFW.glfwMakeContextCurrent(window)
    GL.createCapabilities()

    val context = NanoVGGL3.nvgCreate(0)
    assert(context != NULL)

    while (!GLFW.glfwWindowShouldClose(window)) {
        val width = intArrayOf(0)
        val height = intArrayOf(0)

        GLFW.glfwGetWindowSize(window, width, height)
        GL46C.glViewport(0, 0, width[0], height[0])

        NanoVG.nvgBeginFrame(context, width[0].toFloat(), height[0].toFloat(), 1f)
        drawWindow(context)
        NanoVG.nvgEndFrame(context)
        GLFW.glfwSwapBuffers(window)
        GLFW.glfwWaitEvents()
    }
}
