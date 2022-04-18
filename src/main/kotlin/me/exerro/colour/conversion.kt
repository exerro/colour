package me.exerro.colour

import kotlin.math.max
import kotlin.math.min

/** TODO */
fun Colour.Companion.fromHex(hex: String): Colour = when {
    hex.startsWith("#") -> fromHex(hex.drop(1))
    hex.length in 3 .. 4 -> fromHex(hex.map { "$it$it" }.joinToString(""))
    hex.length == 6 -> fromHex("${hex}ff")
    hex.length == 8 -> fromHex(hex.toLong(radix = 16))
    else -> throw IllegalArgumentException("Invalid hex parameter")
}

/** TODO */
fun Colour.Companion.fromHex(hex: Long) = RGBA(
    red   = (hex shr 24) % 256 / 255f,
    green = (hex shr 16) % 256 / 255f,
    blue  = (hex shr  8) % 256 / 255f,
    alpha = (hex shr  0) % 256 / 255f,
)

/** TODO */
fun Colour.Companion.fromHex(hex: Int) = RGBA(
    red   = (hex shr 24) % 256 / 255f,
    green = (hex shr 16) % 256 / 255f,
    blue  = (hex shr  8) % 256 / 255f,
    alpha = (hex shr  0) % 256 / 255f,
)

/** Convert RGB to HSL.
 *
 *  Algorithm from: [marcocorvi.altervista.org/games/imgpr/rgb-hsl.htm](http://marcocorvi.altervista.org/games/imgpr/rgb-hsl.htm) */
fun Colour.Companion.rgbToHSL(red: Float, green: Float, blue: Float): HSL {
    // HSV algorithms from http://marcocorvi.altervista.org/games/imgpr/rgb-hsl.htm
    val max = max(max(red, green), blue)
    val min = min(min(red, green), blue)
    val l = (max + min) / 2
    val s = when {
        max == min -> 0f
        l < 0.5f -> (max - min) / (max + min)
        else -> (max - min) / (2 - max - min)
    }
    val h = when {
        max == min -> 0f
        red == max -> (green - blue) / (max - min)
        green == max -> 2 + (blue - red) / (max - min)
        else -> 4 + (red - green) / (max - min)
    } cycle 6f
    return HSL(h, s, l)
}

/** Convert RGB to HSL. */
fun Colour.Companion.hslToRGB(hue: Float, saturation: Float, lightness: Float): RGB {
    return if (saturation.isRoughlyZero) {
        RGB(lightness, lightness, lightness)
    }
    else {
        val t2 = if (lightness < 0.5f) lightness * (1 + saturation) else lightness + saturation - lightness * saturation
        val t1 = 2 * lightness - t2
        val h = (hue / 6f) cycle 1f
        val r = ((h + 1 / 3f) cycle 1f).calculateRGBComponent(t1, t2)
        val g = h.calculateRGBComponent(t1, t2)
        val b = ((h - 1 / 3f) cycle 1f).calculateRGBComponent(t1, t2)
        RGB(r, g, b)
    }
}
