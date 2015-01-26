

/**
 * Exercise 2
 */
def balance(chars: List[Char]): Boolean = {

  val lastIndexOfOpen:Int = chars.lastIndexOf("(")
  val openPar: Int = chars.count(_ == "(".apply(0))
  val closePar: Int =chars.count(_ == ")".apply(0))
  var result = false

  if(openPar != closePar) result = false
  else if( openPar == 0 && closePar  == 0 ) result = true
  else  balance( trimList(chars, 1 ))

  def trimList(chars: List[Char], deep: Int) : List[Char] ={
    val firstBit = chars.take(chars.indexOf("("))
    if(firstBit.contains(")") ) "(".toList
      else {
      val secondBit = chars.drop(chars.indexOf("(")+1)
        for(a <- 1 to deep) {
          
        }
    }

  }

  result
}
val test1:List[Char] = "this is balanced".toList
val test2:List[Char] = "()()jazda()))(".toList
val test3:List[Char] = "(ale jazda(1))".toList
balance(test1)
balance(test2)
balance(test3)