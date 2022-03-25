package net.jsparidaans.creative.menger_sponge.sketch

import net.jsparidaans.creative.menger_sponge.model.Box
import net.jsparidaans.creative.runSketch
import processing.core.PApplet


class Sketch : PApplet() {

    var a = 0f

    var sponge: MutableList<Box> = mutableListOf()
    override fun settings() {
        size(600, 600, P3D)
    }

    override fun setup() {
        // Star with one
        val b = Box(this, 0f, 0f, 0f, 200f)
        sponge.add(b)
    }

    override fun mousePressed() {
        val next = mutableListOf<Box>()
        for (b in sponge) {
            val newBoxes = b.generate()
            next.addAll(newBoxes)
        }
        sponge = next
    }

    override fun draw() {
        background(51)
        stroke(255)
        noFill()
        lights()
        translate((width / 2).toFloat(), (height / 2).toFloat())
        rotateX(a)
        rotateY((a * 0.4).toFloat())
        rotateZ((a * 0.1).toFloat())
        // Show what you've got!
        // Show what you've got!
        for (b in sponge) {
            b.show()
        }
        a += 0.01.toFloat()
    }
}

fun main() {
    runSketch<Sketch>()
}

