import scala.math.abs
import scala.util.control._
def solution(A: Array[Int]): Int = {
  // write your code in Scala 2.10
  //wishfull thinking
  var longestQuasiAmplitude = {
    A.length
  }
  //For loop control
  val outer = new Breaks;
  outer.breakable{
    for(i <- 0 to A.length-1){
      var indexCutCombinations = A.indices.combinations(i)
      print(indexCutCombinations)
      for (a <- indexCutCombinations) {

        for(c <- a) {
          var posibleSequence = A.patch(c, Nil,1)
        }
        if (abs(posibleSequence.last - posibleSequence(0)) <= 1) {
          longestQuasiAmplitude = A.length - i
          outer.break;
        }
      }
    }
  }


  longestQuasiAmplitude
}



val testArray = Array(2,3,3)
solution(testArray)