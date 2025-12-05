package beze

import Utils
import java.awt.Color
import java.awt.Graphics
import java.awt.Point
import java.awt.image.BufferedImage


object Beze {

    fun drawBeze(p0: Point, p1: Point, p2: Point, p3: Point): BufferedImage {
        val image = BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB)
        val gr = image.createGraphics()
        gr.color = Color.WHITE
        gr.fillRect(0, 0, 1024, 1024)

        gr.color = Color.BLACK
        Utils.line(gr, p0, p1);
        Utils.line(gr, p1, p2);
        Utils.line(gr, p2, p3);

        bezeLine(gr, p0, p1, p2, p3)

        gr.dispose()
        return image
    }

    private fun bezeLine(gr: Graphics, p0: Point, p1: Point, p2: Point, p3: Point) {
        gr.color = Color.RED

        fun beze(t: Double): Point {
            val t1 = 1 - t
            val x = t1 * t1 * t1 * p0.x +
                    3 * t1 * t1 * t * p1.x +
                    3 * t1 * t * t * p2.x +
                    t * t * t * p3.x

            val y = t1 * t1 * t1 * p0.y +
                    3 * t1 * t1 * t * p1.y +
                    3 * t1 * t * t * p2.y +
                    t * t * t * p3.y

            return Point(x.toInt(), y.toInt())
        }

        var perv = p0
        val st = getSteps(p0, p1, p2, p3)
        for (i in 1..st) {
            val t = i.toDouble() / st
            val p = beze(t)
            Utils.line(gr, perv, p)

            perv = p
        }
    }

    fun getSteps(p0: Point, p1: Point, p2: Point, p3: Point): Int {
        var dxy = 1
        dxy += Math.abs(p1.x - p0.x) + Math.abs(p1.y - p0.y)
        dxy += Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y)
        dxy += Math.abs(p3.x - p2.x) + Math.abs(p3.y - p2.y)

//        println(dxy)
        return Math.max(20, dxy / 5)
    }
}
