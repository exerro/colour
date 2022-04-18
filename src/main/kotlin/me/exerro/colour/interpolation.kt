package me.exerro.colour

import kotlin.math.pow

/** Linearly interpolate two colours in the RGBA space. [t] should range between
 *  0 and 1, where 0 will map to [a], 1 will map to [b], and 0.5 will map to the
 *  linear RGBA average of both colours. */
fun Colour.Companion.lerpRGBA(t: Float, a: Colour, b: Colour) = RGBA(
    red = a.red * (1 - t) + b.red * t,
    green = a.green * (1 - t) + b.green * t,
    blue = a.blue * (1 - t) + b.blue * t,
    alpha = a.alpha * (1 - t) + b.alpha * t,
)

/** Linearly interpolate two colours in the HSLA space. [t] should range between
 *  0 and 1, where 0 will map to [a], 1 will map to [b], and values in between
 *  will linearly interpolate the HSLA values. Since [hue][Colour.hue] is
 *  cyclic, this function interpolates in the "shortest direction" e.g. with
 *  [t] = 0.5, [a].hue = 5.9 and [b].hue = 0.1, the resultant hue will be 0
 *  instead of 3. */
fun Colour.Companion.lerpHSLA(t: Float, a: Colour, b: Colour) = HSLA(
    hue = when {
        a.hue < b.hue && b.hue - a.hue > 3f -> (a.hue * (1 - t) + (b.hue - 6) * t) cycle 6f
        a.hue > b.hue && a.hue - b.hue > 3f -> ((a.hue - 6) * (1 - t) + (b.hue) * t) cycle 6f
        else -> a.hue * (1 - t) + b.hue * t
    },
    saturation = a.saturation * (1 - t) + b.saturation * t,
    lightness = a.lightness * (1 - t) + b.lightness * t,
    alpha = a.alpha * (1 - t) + b.alpha * t,
)

/** Interpolate two colours in the RGBA space with gamma correction. */
fun Colour.Companion.mixRGBA(t: Float, a: Colour, b: Colour, gamma: Float = 2.2f) = RGBA(
    red = (a.red.pow(gamma) * (1 - t) + b.red.pow(gamma) * t).pow(1f/gamma),
    green = (a.green.pow(gamma) * (1 - t) + b.green.pow(gamma) * t).pow(1f/gamma),
    blue = (a.blue.pow(gamma) * (1 - t) + b.blue.pow(gamma) * t).pow(1f/gamma),
    alpha = a.alpha * (1 - t) + b.alpha * t,
)
