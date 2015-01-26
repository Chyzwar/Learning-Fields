
/**
 * Exercise 2
 */
def balance(chars: List[Char]): Boolean = {

  val lastIndexOfOpen:Int = chars.lastIndexOf("(")
  val openPar: Int = chars.count(_ == "(".apply(0))
  val closePar: Int =chars.count(_ == ")".apply(0))


  if( openPar == 0 && closePar) = 0 ) result = false else


    def balanceIter(chars: List[Chars])

  println(openPar.toString ++ " " ++ closePar.toString)

//  def balanceIter()

  true
}
val test1:List[Char] = "this is balanced".toList
val test2:List[Char] = "()()jazda()))(".toList
val test3:List[Char] = "(ale jazda(1))".toList
balance(test1)
balance(test2)
balance(test3)