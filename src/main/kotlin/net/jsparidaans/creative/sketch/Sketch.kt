package net.jsparidaans.creative.sketch

import processing.core.PApplet
import processing.core.PVector

class Sketch : PApplet() {
    private val order = 4
    private val N = (pow(2f, order.toFloat())).toInt()
    private val total = N * N

    private val path = arrayOfNulls<PVector>(total)
    override fun settings() {
        size(768, 768)

    }

    override fun setup() {
        colorMode(HSB, 360f, 255f, 255f)
        background(0)
        val len = (width / N).toFloat()
        for (index in 0 until total) {
            path[index] = hilbert(index)
            path[index]?.mult(len)
            path[index]?.add(len / 2, len / 2)
        }
        print(total)
    }

    private var counter = 0
    override fun draw() {
        background(0)

        stroke(360f, 255f, 255f)
        strokeWeight(8f)
        point(path[0]?.x!!, path[0]?.y!!)

        stroke(255)
        strokeWeight(2f)
        noFill()
        for (i in 1 until path.size) {
            val h = map(i.toFloat(), 0f, path.size.toFloat(), 0f, 360f)
            stroke(h, 255f, 255f)
            strokeWeight(8f)
            point(path[i]?.x!!, path[i]?.y!!)
            strokeWeight(2f)
            line(
                path[i]?.x!!,
                path[i]?.y!!,
                path[i - 1]?.x!!,
                path[i - 1]?.y!!,
            )

        }
        counter++
        if (counter >= path.size) {
            counter = 0;
        }
        for (i in 0 until path.size) {
//            text(i, path[i]?.x!! + 5, path[i]?.y!!)
        }
    }

    fun hilbert(i: Int): PVector {
        var i = i
        val vectors = listOf(
            PVector(0f, 0f),
            PVector(0f, 1f),
            PVector(1f, 1f),
            PVector(1f, 0f),
        )
        var index = i and 3
        val vector = vectors[index]
        for (j in 1 until order) {
            i = i shr 2
            index = i and 3
            val len = pow(2f, j.toFloat())
            when (index) {
                0 -> {
                    val temp = vector.x
                    vector.x = vector.y
                    vector.y = temp
                }
                1 -> vector.y += len
                2 -> {
                    vector.x += len
                    vector.y += len
                }
                3 -> {
                    val temp = len - 1 - vector.x
                    vector.x = len - 1 - vector.y
                    vector.y = temp
                    vector.x += len
                }
            }
        }
        return vector
    }
}

fun main() {
    PApplet.main("net.jsparidaans.creative.sketch.Sketch")
}