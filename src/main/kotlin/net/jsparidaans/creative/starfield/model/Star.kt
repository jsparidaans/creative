package net.jsparidaans.creative.starfield.model

import processing.core.PApplet
import processing.core.PApplet.map


data class Star(
    val window: PApplet,
    var x: Float = (-window.width / 2..window.width / 2).random().toFloat(),
    var y: Float = (-window.height / 2..window.height / 2).random().toFloat(),
    var z: Float = (0..window.width / 2).random().toFloat(),
    var pz: Float = z,
) {
    fun update(speed: Float) {
        window.apply {
            // In the formula to set the new stars coordinates
            // I'll divide a value for the "z" value and the outcome will be
            // the new x-coordinate and y-coordinate of the star.
            // Which means if I decrease the value of "z" (which is a divisor),
            // the outcome will be bigger.
            // Wich means the more the speed value is bigger, the more the "z" decrease,
            // and the more the x and y coordinates increase.
            // Note: the "z" value is the first value I updated for the new frame.
            z -= speed
            // when the "z" value equals to 1, I'm sure the star have passed the
            // borders of the canvas( probably it's already far away from the borders),
            // so i can place it on more time in the canvas, with new x, y and z values.
            // Note: in this way I also avoid a potential division by 0.
            if (z < 1) {
                z = (width / 2).toFloat()
                x = random((-width / 2).toFloat(), (width / 2).toFloat())
                y = random((-height / 2).toFloat(), (height / 2).toFloat())
                pz = z
            }
        }

    }

    fun show() {
        window.apply {
            fill(255)
            noStroke()

            val sx: Float = map(x / z, 0f, 1f, 0f, (width / 2).toFloat())
            val sy: Float = map(y / z, 0f, 1f, 0f, (height / 2).toFloat())

            val r: Float = map(z, 0f, (width / 2).toFloat(), 16f, 0f)
            ellipse(sx, sy, r, r)

            val px: Float = map(x / pz, 0f, 1f, 0f, (width / 2).toFloat())
            val py: Float = map(y / pz, 0f, 1f, 0f, (height / 2).toFloat())

            pz = z

            stroke(255)
            line(px, py, sx, sy)
        }
    }
}
