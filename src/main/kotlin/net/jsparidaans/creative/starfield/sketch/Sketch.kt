package net.jsparidaans.creative.starfield.sketch

import net.jsparidaans.creative.starfield.model.Star
import processing.core.PApplet

class Sketch : PApplet() {

    private var speed = 0f
    private val stars: MutableList<Star> = mutableListOf()
    override fun settings() {
        size(600, 600)

    }

    override fun setup() {
        for (i in 0..800) {
            stars.add(Star(this))
        }
    }

    override fun draw() {
        // I link the value of the speed variable to the mouse position.
        speed = map(mouseX.toFloat(), 0f, width.toFloat(), 0f, 50f)

        background(0)
        // I shift the entire composition,
        // moving its center from the top left corner to the center of the canvas.
        translate((width / 2).toFloat(), (height / 2).toFloat())
        // I draw each star, running the "update" method to update its position and
        // the "show" method to show it on the canvas.
        for (i in 0 until stars.size) {
            stars[i].update(speed)
            stars[i].show()
        }
    }
}

fun main() {
    PApplet.main("net.jsparidaans.creative.starfield.sketch.Sketch")
}