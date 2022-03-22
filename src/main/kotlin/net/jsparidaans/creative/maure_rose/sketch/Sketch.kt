package net.jsparidaans.creative.maure_rose.sketch

import processing.core.PApplet

var n = 0f
var d = 0f
var backward = false
var range = 10f
var hue = 0f

class Sketch : PApplet() {
    override fun settings() {
        fullScreen()
    }

    override fun draw() {
        colorMode(HSB, 360f, 255f, 255f)
        background(0)
        translate((width / 2).toFloat(), (height / 2).toFloat())

        stroke(hue, 255f, 255f)
        beginShape()
        strokeWeight(4f)
        noFill()
        for (i in 0..360) {
            val k: Float = i * d
            val r = 150 * sin(radians(n * k))
            val x = r * cos(radians(k))
            val y = r * sin(radians(k))
            vertex(x, y)
        }
        endShape()

        noFill()

        if (n in -range..range || d in -range..range) {
            if (backward) {
                n -= 0.001f
                d -= 0.003f
                hue -= 0.1f

            } else if (!backward) {
                n += 0.001f
                d += 0.003f
                hue += 0.1f
            }
        }
        if (n !in -range..range || d !in -range..range) {
            backward = !backward
            range += .5f
        }
        if (hue >= 360) {
            hue = 0f
        }

    }
}

fun main() {
    PApplet.main("net.jsparidaans.creative.maure_rose.sketch.Sketch")
}