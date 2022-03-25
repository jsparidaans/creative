package net.jsparidaans.creative

import processing.core.PApplet

inline fun <reified T> runSketch(){
    PApplet.main(T::class.java.name)
}