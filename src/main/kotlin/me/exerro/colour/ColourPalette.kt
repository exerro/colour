package me.exerro.colour

/** TODO */
interface ColourPalette {
    val background0: Colour
    val background1: Colour
    val background2: Colour
    val background3: Colour

    val grey0: Colour
    val grey1: Colour
    val grey2: Colour

    val foreground0: Colour
    val foreground1: Colour
    val foreground2: Colour
    val foreground3: Colour

    val shadow: Colour

    val red: Colour
    val green: Colour
    val blue: Colour
    val orange: Colour
    val yellow: Colour
    val purple: Colour

    // TODO: maybe replace these with generic alt colours which can be palette
    //       dependent
    val pink: Colour
    val burgundy: Colour
    val salmon: Colour
    val teal: Colour
    val brown: Colour
    val cream: Colour

    /** TODO */
    companion object: ColourPalette {
        /** TODO */
        val transparent = RGBA(0f, 0f, 0f, 0f)

        override val background0 = Greyscale(0.08f)
        override val background1 = Greyscale(0.12f)
        override val background2 = Greyscale(0.16f)
        override val background3 = Greyscale(0.20f)
        override val grey0 = Greyscale(0.32f)
        override val grey1 = Greyscale(0.38f)
        override val grey2 = Greyscale(0.44f)
        override val foreground0 = Greyscale(0.78f)
        override val foreground1 = Greyscale(0.84f)
        override val foreground2 = Greyscale(0.90f)
        override val foreground3 = Greyscale(0.96f)
        override val shadow = Greyscale(0f, alpha = 0.6f)
        override val red = RGBA(230, 70, 70)
        override val green = RGBA(70, 175, 70)
        override val blue = RGBA(40, 120, 220)
        override val orange = RGBA(240, 135, 35)
        override val yellow = RGBA(230, 200, 50)
        override val purple = RGBA(110, 60, 200)
        override val pink = RGBA(240, 140, 170)
        override val burgundy = Colour.fromHex("#735")
        override val salmon = RGBA(220, 130, 140)
        override val teal = RGBA(40, 150, 190)
        override val brown = Colour.fromHex("66332e")
        override val cream = Colour.fromHex("eda")

        /** TODO */
        operator fun invoke(
            background0: Colour = this.background0,
            background1: Colour = this.background1,
            background2: Colour = this.background2,
            background3: Colour = this.background3,
            grey0: Colour = this.grey0,
            grey1: Colour = this.grey1,
            grey2: Colour = this.grey2,
            foreground0: Colour = this.foreground0,
            foreground1: Colour = this.foreground1,
            foreground2: Colour = this.foreground2,
            foreground3: Colour = this.foreground3,
            shadow: Colour = this.shadow,
            red: Colour = this.red,
            green: Colour = this.green,
            blue: Colour = this.blue,
            orange: Colour = this.orange,
            yellow: Colour = this.yellow,
            purple: Colour = this.purple,
            pink: Colour = this.pink,
            burgundy: Colour = this.burgundy,
            salmon: Colour = this.salmon,
            teal: Colour = this.teal,
            brown: Colour = this.brown,
            cream: Colour = this.cream,
        ) = object: ColourPalette {
            override val background0: Colour = background0
            override val background1: Colour = background1
            override val background2: Colour = background2
            override val background3: Colour = background3
            override val grey0: Colour = grey0
            override val grey1: Colour = grey1
            override val grey2: Colour = grey2
            override val foreground0: Colour = foreground0
            override val foreground1: Colour = foreground1
            override val foreground2: Colour = foreground2
            override val foreground3: Colour = foreground3
            override val shadow: Colour = shadow
            override val red: Colour = red
            override val green: Colour = green
            override val blue: Colour = blue
            override val orange: Colour = orange
            override val yellow: Colour = yellow
            override val purple: Colour = purple
            override val pink: Colour = pink
            override val burgundy: Colour = burgundy
            override val salmon: Colour = salmon
            override val teal: Colour = teal
            override val brown: Colour = brown
            override val cream: Colour = cream
        }
    }
}
