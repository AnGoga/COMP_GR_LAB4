package kirysa_beke

import Utils
import java.awt.Color
import java.awt.Point
import java.awt.image.BufferedImage
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Vector(val x: Double, val y: Double) {
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())
    constructor(p: Point) : this(p.x.toDouble(), p.y.toDouble())

    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
    operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)
    operator fun times(scalar: Double) = Vector(x * scalar, y * scalar)
    infix fun scalarMulti(other: Vector): Double = x * other.x + y * other.y

    fun toPoint(): Point = Point(x.toInt(), y.toInt())
}

object KirysaBeke {
    fun solve(line: Pair<Point, Point>, poly: List<Point>): BufferedImage {
        val image = BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB)
        val gr = image.createGraphics()
        gr.color = Color.WHITE
        gr.fillRect(0, 0, 1024, 1024)

        gr.color = Color.BLUE
        for (i in poly.indices) {
            Utils.line(gr, poly[i], poly[(i + 1) % poly.size])
        }
        gr.color = Color.BLACK
        Utils.line(gr, line.first, line.second)

        val res = kirysa_beke(line, poly)
        if (res != null) {
            gr.color = Color.RED
            Utils.line(gr, res.first.toPoint(), res.second.toPoint())
        }

        gr.dispose()
        return image
    }

    fun kirysa_beke(line: Pair<Point, Point>, pol: List<Point>): Pair<Vector, Vector>? {
        val p0 = Vector(line.first)
        val dir = Vector(line.second) - p0

        var tstart = 0.0
        var tend = 1.0

        for (i in pol.indices) {
            val v0 = Vector(pol[i])
            val v1 = Vector(pol[(i + 1) % pol.size])
            val v2 = Vector(pol[(i + 2) % pol.size])

            val tmp = v1 - v0
            val n: Double = Vector(-tmp.y, tmp.x) scalarMulti (p0 - v0)
            val d: Double = Vector(-tmp.y, tmp.x) scalarMulti dir

            if (abs(d) <= 1e-8) {
                if (n >= 0) continue
                return null
            }

            val t = -n / d
            if (d > 0) {
                tstart = max(tstart, t)
            } else {
                tend = min(tend, t)
            }

            if (tstart > tend) return null
        }

        return Pair(p0 + dir * tstart, p0 + dir * tend)
    }
}
