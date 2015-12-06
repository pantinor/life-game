Game of Life - Java
==========

An exercise to learn Slick2D and TWL by recreating the Game of Life (Hasbro game).  

I used awesome sprites from this website: http://www.reinerstilesets.de/

Maven Notes:

TWL was not in any maven repo so I added the source locally with this project.

TO run in IDE, I used these settings, ie in nbactions.xml:

-Djava.library.path=./target/classes -classpath %classpath org.antinori.game.slick.LifeGame

![screenshot of the example](https://raw.github.com/pantinor/life-game/master/example1.png)