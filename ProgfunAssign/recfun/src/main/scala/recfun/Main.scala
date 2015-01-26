package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    val pascalLine: List[Int] = List(1)
    val row: Int = 0

    def pascalIter(line: List[Int], searchRow: Int): Int = {
      if (isPascalLine(searchRow)) line(c)
      else (pascalIter(getPascalLine(line), increment(searchRow)))
    }

    def isPascalLine(searchRow: Int): Boolean =
      searchRow == r


    def getPascalLine(line: List[Int]): List[Int] = {
      if (line.length == 0) List(1)
      else if(line.length == 1){
        List(1,1)
      }
      else{
        val vectorPascal = line.toVector
        val l = for (i <- 0 to (vectorPascal.length -2)) yield vectorPascal(i) + vectorPascal(i + 1)
        val finalPascalLine =  Vector(1) ++  l ++ Vector(1)
        finalPascalLine.toList
      }

    }

    def increment(x: Int): Int = x + 1

    pascalIter(pascalLine, 0)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {

     val lastIndexOfOpen:Int = chars.lastIndexOf("(")

      print(lastIndexOfOpen)

//    def countTest(chars : List[Char]): Boolean
       true
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = ???
}
