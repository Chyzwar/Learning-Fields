import scala.math.abs

val testArrays1: Array[Int] = Array(3, 3, 2, 3, 3)
val testArrays2 = Array(3, 1, 2, 4, 3)

//Shity performance but works !! :p
def solution(a: Array[Int])= {



  def leftSum(index: Int)  = {
    var sum: Long = 0
    for(i <- 0 to index-1){
      sum = sum + a(i)
    }
    sum
  }
  def rightSum(index: Int) = {
    var sum: Long = 0
    for(i <- index to a.length-1){
      sum = sum + a(i)
    }
    sum
  }

  var tapeEqulibrium = {
    if (a.length > 1)
      abs(leftSum(0) - rightSum(0))
    else
      -1
  }

  def tapeEquilibriumSearch(index: Int) = {
    val localTapeEqulibrium = abs(leftSum(index) - rightSum(index))
    if( localTapeEqulibrium < tapeEqulibrium){
      tapeEqulibrium = localTapeEqulibrium
    }
  }


  var b = 0
  if (a.length > 1) {
    for (b <- 1 to a.length - 1) {
      tapeEquilibriumSearch(b)
    }
    tapeEqulibrium.toInt
  }
  else{
    tapeEqulibrium.toInt
  }

}




solution(testArrays1)
solution(testArrays2)