package me.exerro.colour

import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/** Abstract Colour interface with an alpha component and both RGB/HSL colour
 *  components.
 *
 *  @see RGBA
 *  @see HSLA
 *  @see Greyscale
 */
interface Colour {
    /** Alpha (transparency) component in the range 0-1. */
    val alpha: Float

    /** Perceived brightness of the colour.
     *
     *  See: https://stackoverflow.com/questions/596216/formula-to-determine-brightness-of-rgb-color
     *
     *  See: https://www.w3.org/TR/AERT/#color-contrast */
    val brightness: Float get() = 0.299f * red + 0.587f * green + 0.114f * blue

    /** RGB components of the colour. All components are in the range 0-1.
     *  See [red], [green], [blue]. */
    val rgb: RGB

    /** Red component, see [rgb]. */
    val red: Float get() = rgb.red

    /** Green component, see [rgb]. */
    val green: Float get() = rgb.green

    /** Blue component, see [rgb]. */
    val blue: Float get() = rgb.blue

    /** HSL components of the colour. See [hue], [saturation], [lightness]. */
    val hsl: HSL

    /** Hue component, in the range 0-6. See [hsl]. */
    val hue: Float get() = hsl.hue

    /** Saturation component, in the range 0-1. See [hsl]. */
    val saturation: Float get() = hsl.saturation

    /** Lightness component, in the range 0-1. See [hsl]. */
    val lightness: Float get() = hsl.lightness

    /** Return a copy of the colour with modified values. Note, this does not
     *  synchronise the values in any way and should therefore be used
     *  carefully. */
    fun copy(
        alpha: Float = this.alpha, brightness: Float = this.brightness,
        rgb: RGB = this.rgb, red: Float = this.red, green: Float = this.green, blue: Float = this.blue,
        hsl: HSL = this.hsl, hue: Float = this.hue, saturation: Float = this.saturation, lightness: Float = this.lightness,
    ) = object: Colour {
        override val alpha = alpha
        override val brightness = brightness
        override val rgb = rgb
        override val red = red
        override val green = green
        override val blue = blue
        override val hsl = hsl
        override val hue = hue
        override val saturation = saturation
        override val lightness = lightness
    }

    /** Return the same colour with a different alpha value. */
    fun withAlpha(alpha: Float) = copy(alpha = alpha)

    /** @return the colour with a specified brightness */
    fun withBrightness(brightness: Float): Colour {
        val scale = brightness / this.brightness
        return RGBA(red * scale, green * scale, blue * scale, alpha)
    }

    /** Return this colour gamma encoded.
     *
     *  Colours are generally in the sRGB space (e.g. photos from a camera,
     *  pictures on the web, frame-buffers for your monitor). However, the sRGB
     *  space is not linear in terms of perceived brightness, meaning
     *  interpolating in this space and darkening/lightening doesn't work as
     *  expected.
     *
     *  Gamma encoding with [gamma]=2.2f will roughly map a space that is linear
     *  in perceived brightness to sRGB, allowing colours that have been
     *  interpolated or operated on to be converted back into the standard sRGB
     *  space.
     *
     *  Read about gamma encoding at [blog.johnnovak.net/2016/09/21/what-every-coder-should-know-about-gamma](https://blog.johnnovak.net/2016/09/21/what-every-coder-should-know-about-gamma/) */
    fun gammaEncode(gamma: Float = 2.2f) = RGBA(
        red = red.pow(1f/gamma),
        green = green.pow(1f/gamma),
        blue = blue.pow(1f/gamma),
        alpha = alpha,
    )

    /** Return this colour gamma decoded.
     *
     *  Colours are generally in the sRGB space (e.g. photos from a camera,
     *  pictures on the web, frame-buffers for your monitor). However, the sRGB
     *  space is not linear in terms of perceived brightness, meaning
     *  interpolating in this space and darkening/lightening doesn't work as
     *  expected.
     *
     *  Gamma decoding with [gamma]=2.2f will roughly map sRGB into a space
     *  linear in perceived brightness, allowing these operations to make sense.
     *
     *  Read about gamma encoding at [blog.johnnovak.net/2016/09/21/what-every-coder-should-know-about-gamma](https://blog.johnnovak.net/2016/09/21/what-every-coder-should-know-about-gamma/) */
    fun gammaDecode(gamma: Float = 2.2f) = RGBA(
        red = red.pow(gamma),
        green = green.pow(gamma),
        blue = blue.pow(gamma),
        alpha = alpha,
    )

    /** Return a lighter variant of the colour. */
    fun lighten(amount: Float = 0.1f) =
        withBrightness(min(1f, brightness + amount))

    /** Return a darker variant of the colour. */
    fun darken(amount: Float = 0.1f) =
        withBrightness(max(0f, brightness - amount))

    ////////////////////////////////////////////////////////////////////////////

    /** @see Colour */
    companion object
}
