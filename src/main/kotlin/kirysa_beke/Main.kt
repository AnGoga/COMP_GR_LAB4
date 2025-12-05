package kirysa_beke

import java.awt.Point
import java.io.File
import javax.imageio.ImageIO

fun main() {
    val p3 = listOf(
        Point(512, 100),
        Point(900, 800),
        Point(124, 800)
    )
    val p4 = listOf(
        Point(200, 200),
        Point(800, 200),
        Point(800, 800),
        Point(200, 800)
    )
    val p6 = listOf(
        Point(512, 150),
        Point(750, 300),
        Point(750, 600),
        Point(512, 750),
        Point(274, 600),
        Point(274, 300)
    )

    listOf(
        Triple(p3, Point(100, 500), Point(900, 500)),
        Triple(p3, Point(512, 50), Point(512, 900)),
        Triple(p3, Point(200, 300), Point(800, 700)),
        Triple(p3, Point(400, 600), Point(600, 600)),

        Triple(p4, Point(100, 500), Point(900, 500)),
        Triple(p4, Point(300, 100), Point(700, 900)),
        Triple(p4, Point(100, 100), Point(900, 900)),
        Triple(p4, Point(400, 400), Point(600, 600)),
        Triple(p4, Point(50, 50), Point(100, 100)),
        Triple(p4, Point(100, 300), Point(300, 100)),
        Triple(p4, Point(500, 100), Point(500, 900)),
        Triple(p4, Point(150, 150), Point(850, 850)),

        Triple(p6, Point(393,75), Point(869,375)),
        Triple(p6, Point(100, 450), Point(900, 450)),
        Triple(p6, Point(512, 100), Point(512, 800)),
        Triple(p6, Point(200, 200), Point(800, 700)),
    ).forEachIndexed { index, (poly, p1, p2) ->
        val file = File("src/main/resources/kirysa_beka/res-$index.png")
        file.parentFile?.mkdirs()
        ImageIO.write(KirysaBeke.solve(Pair(p1, p2), poly), "png", file)
    }
}
