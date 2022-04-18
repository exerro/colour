package me.exerro.colour

import kotlin.math.nextDown

/** @return Whether this number is roughly zero. */
internal val Float.isRoughlyZero get() = this > -0.000_001f && this < 0.000_001f

/** @return Whether this number is roughly zero. */
internal infix fun Float.cycle(v: Float): Float {
    var r = this % v
    while (r < 0) r += v
    while (r >= v) r -= v
    return r
}

/** @return some fancy thing that does HSL to RGB conversion */
internal fun Float.calculateRGBComponent(temp1: Float, temp2: Float) = when {
    this < 1/6f -> temp1 + (temp2 - temp1) * 6 * this
    this < 1/2f -> temp2
    this < 2/3f -> temp1 + (temp2 - temp1) * (2/3f - this) * 6
    else -> temp1
}

internal fun randomFloat01() =
    (Math.random() / 1.0.nextDown()).toFloat()
