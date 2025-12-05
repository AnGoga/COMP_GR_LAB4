package beze

import java.awt.Point
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {

    listOf(
        listOf(Point(100, 800), Point(300, 100), Point(700, 100), Point(900, 800)),
        listOf(Point(0, 0), Point(1000, 1000), Point(1000, 1000), Point(0, 1000)),
        listOf(Point(512, 512), Point(512, 512), Point(512, 512), Point(512, 512)),
        listOf(Point(0, 512), Point(0, 512), Point(512, 0), Point(512, 0)),
        listOf(Point(100, 500), Point(300, 100), Point(700, 900), Point(900, 500)),
        listOf(Point(100, 700), Point(300, 500), Point(700, 500), Point(900, 700)),
        listOf(Point(100, 200), Point(300, 800), Point(700, 800), Point(900, 200)),
        listOf(Point(100, 500), Point(300, 520), Point(700, 480), Point(900, 500)),
        listOf(Point(100, 800), Point(400, 100), Point(600, 100), Point(900, 800)),
        listOf(Point(100, 300), Point(200, 900), Point(800, 100), Point(900, 700)),
        listOf(Point(500, 900), Point(200, 300), Point(800, 300), Point(500, 900)),
        listOf(Point(100, 900), Point(100, 100), Point(900, 100), Point(900, 900)),
        listOf(Point(100, 500), Point(400, 400), Point(700, 600), Point(900, 500)),
        listOf(Point(900, 500), Point(600, 300), Point(400, 700), Point(100, 500))
    ).forEachIndexed { index, it ->
        ImageIO.write(
            Beze.drawBeze(it[0], it[1], it[2], it[3]),
            "png",
            File("src/main/resources/beze/res-$index.png").also { it.createNewFile() }
        )
    }
}