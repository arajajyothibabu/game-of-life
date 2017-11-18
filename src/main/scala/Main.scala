/**
  * Created by jyothi on 18/11/17.
  */

/**
  * 1. Any Live cell with neighbours fewer than 2 are dead
  * 2. Any Live cell with more than 3 live neighbours dies of over population
  * 3. Any dead cell with exactly 3 live neighbours comes to life of warmth
  */

object Main extends App {

  val panel = (0 until 5).map(x => (0 until 5).map(y => 0).toArray).toArray
  panel(2)(2) = 1
  panel(1)(2) = 1
  panel(3)(2) = 1

  val gameOfLife = new GameOfLife(panel)

  gameOfLife.printPanel() //printing input panel

  gameOfLife.nextGeneration()

  gameOfLife.printPanel() //printing output panel

  gameOfLife.nextGeneration()

  gameOfLife.printPanel() //printing output panel

  gameOfLife.nextGeneration()

  gameOfLife.printPanel() //printing output panel

  gameOfLife.nextGeneration()

  gameOfLife.printPanel() //printing output panel

}