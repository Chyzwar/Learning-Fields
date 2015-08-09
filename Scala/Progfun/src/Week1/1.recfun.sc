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