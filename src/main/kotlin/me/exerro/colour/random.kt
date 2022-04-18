package me.exerro.colour

/** TODO */
fun Colour.Companion.random(alpha: Float = 1f) = RGBA(
    red = randomFloat01(),
    green = randomFloat01(),
    blue = randomFloat01(),
    alpha = alpha,
)

/** TODO */
fun Colour.Companion.randomRGBA(
    red: Float? = null,
    green: Float? = null,
    blue: Float? = null,
    alpha: Float = 1f,
) = RGBA(
    red = red ?: randomFloat01(),
    green = green ?: randomFloat01(),
    blue = blue ?: randomFloat01(),
    alpha = alpha,
)

/** TODO */
fun Colour.Companion.randomHSLA(
    hue: Float? = null,
    saturation: Float? = null,
    lightness: Float? = null,
    alpha: Float = 1f,
) = HSLA(
    hue = hue ?: (Math.random().toFloat() * 6f),
    saturation = saturation ?: randomFloat01(),
    lightness = lightness ?: randomFloat01(),
    alpha = alpha,
)
