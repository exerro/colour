package me.exerro

import me.exerro.colour.Colour
import me.exerro.colour.ColourPalette
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

fun drawPalette(
    context: Long,
    palette: ColourPalette,
) {
    // background0
    // background1
    // background2
    // background3

    // grey0
    // grey1
    // grey2

    // foreground0
    // foreground1
    // foreground2
    // foreground3

    // shadow

    rect(context, palette.purple, 32f, 128f, 128f, 64f)
    rect(context, palette.red, 192f, 128f, 128f, 64f)
    rect(context, palette.orange, 352f, 128f, 128f, 64f)
    rect(context, palette.yellow, 512f, 128f, 128f, 64f)
    rect(context, palette.green, 672f, 128f, 128f, 64f)
    rect(context, palette.blue, 832f, 128f, 128f, 64f)

    rect(context, palette.teal, 32f, 256f, 128f, 64f)
    rect(context, palette.burgundy, 192f, 256f, 128f, 64f)
    rect(context, palette.pink, 352f, 256f, 128f, 64f)
    rect(context, palette.salmon, 512f, 256f, 128f, 64f)
    rect(context, palette.brown, 672f, 256f, 128f, 64f)
    rect(context, palette.cream, 832f, 256f, 128f, 64f)

    rect(context, palette.grey0, 32f, 384f, 128f, 64f)
    rect(context, palette.grey1, 192f, 384f, 128f, 64f)
    rect(context, palette.grey2, 352f, 384f, 128f, 64f)
    rect(context, palette.foreground0, 512f, 384f, 128f, 64f)
    rect(context, palette.foreground1, 672f, 384f, 128f, 64f)
    rect(context, palette.foreground2, 832f, 384f, 128f, 64f)
    rect(context, palette.foreground3, 992f, 384f, 128f, 64f)
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

    GL46C.glClearColor(ColourPalette.background1.red, ColourPalette.background1.green, ColourPalette.background1.blue, 1f)
    GL46C.glClear(GL46C.GL_COLOR_BUFFER_BIT)

    drawPalette(context, ColourPalette)

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
