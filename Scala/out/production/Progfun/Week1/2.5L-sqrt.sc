
def sqrtIter(guess: Double): Double =
  if (isGoodEnough(guess, x)) guess
  else sqrtIter(improve(guess, x), x)


def isGooodEnough(guess: Double, x: Double)=
abs(guess*guess-x) < 0.001