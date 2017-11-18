/**
  * Created by jyothi on 18/11/17.
  */

case class Cell(x: Int, y: Int)

class GameOfLife(inputPanel: Array[Array[Int]] = Array()) {

  val alive = 1
  val dead = 0

  private val defaultGamePanel = (0 until 5).map(x => (0 until 5).map(y => dead).toArray).toArray

  private val gamePanel = if(inputPanel.length == 0) defaultGamePanel else inputPanel

  private val sizeOfPanel = gamePanel.length

  def printPanel(): Unit = gamePanel.foreach(x => println(x.mkString(" ")))

  def numberOfNeighbours(x: Int, y: Int): Int = {
    var neighbours = 0
    if(x - 1 > -1 && y - 1 > -1) neighbours += gamePanel(x - 1)(y - 1) //(x - 1, y - 1)
    if(x - 1 > -1) neighbours += gamePanel(x - 1)(y) //(x - 1, y)
    if(x - 1 > -1 && y + 1 < sizeOfPanel) neighbours += gamePanel(x - 1)(y + 1) //(x - 1, y + 1)
    if(y - 1 > -1) neighbours += gamePanel(x)(y - 1) //(x, y - 1)
    if(y + 1 < sizeOfPanel) neighbours += gamePanel(x)(y + 1) //(x, y + 1)
    if(x + 1 < sizeOfPanel && y - 1 > -1) neighbours += gamePanel(x + 1)(y - 1) //(x + 1, y - 1)
    if(x + 1 < sizeOfPanel) neighbours += gamePanel(x + 1)(y) //(x + 1, y)
    if(x + 1 < sizeOfPanel && y + 1 < sizeOfPanel) neighbours += gamePanel(x + 1)(y + 1) //(x + 1, y + 1)
    neighbours
  }

  private def ruleOne(x: Int, y: Int): Boolean = numberOfNeighbours(x, y) < 2

  private def ruleTwo(x: Int, y: Int): Boolean = numberOfNeighbours(x, y) > 3

  private def ruleThree(x: Int, y: Int): Boolean = numberOfNeighbours(x, y) == 3

  def killAlive(cell: Cell): Unit = {
    gamePanel(cell.x)(cell.y) = dead
  }

  def giveBirth(cell: Cell): Unit = {
    gamePanel(cell.x)(cell.y) = alive
  }

  def nextGeneration(): Unit = {
    var readyToDie = List[Cell]()
    var readyToBorn = List[Cell]()
    for(x <- gamePanel.indices; y <- gamePanel(x).indices){
      if(ruleOne(x, y)){
        readyToDie ::= Cell(x, y)
      }
      if(ruleTwo(x, y)){
        readyToDie ::= Cell(x, y)
      }
      if(ruleThree(x, y)){
        readyToBorn ::= Cell(x, y)
      }
    }
    readyToDie.foreach(killAlive)
    readyToBorn.foreach(giveBirth)
  }

}
