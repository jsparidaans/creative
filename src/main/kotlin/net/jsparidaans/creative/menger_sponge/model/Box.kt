package net.jsparidaans.creative.menger_sponge.model

import processing.core.PApplet
import processing.core.PApplet.abs
import processing.core.PVector


data class Box(val window: PApplet, val x: Float, val y: Float, val z: Float, val r_: Float) {
    private var pos: PVector = PVector(x, y, z)
    private var r: Float = r_

    fun generate(): MutableList<Box> {
        val boxes = mutableListOf<Box>()
        for (x in -1..1) {
            for (y in -1..1) {
                for (z in -1..1) {
                    val sum: Int = abs(x) + abs(y) + abs(z)
                    val newR = r / 3
                    if (sum > 1) {
                        val b = Box(window, pos.x + x * newR, pos.y + y * newR, pos.z + z * newR, newR)
                        boxes.add(b)
                    }
                }
            }
        }
        return boxes
    }

    fun show() {
        window.apply {
            pushMatrix()
            translate(pos.x, pos.y, pos.z)
            noStroke()
            fill(255)
            box(r)
            popMatrix()
        }
    }
}