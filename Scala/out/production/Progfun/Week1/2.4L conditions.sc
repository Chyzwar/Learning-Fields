def and(x: Boolean , y: Boolean) = if(x) if(y) true else false else false

and(true,true)
and(true,false)
and(false,false)

def or(x: Boolean , y: Boolean) = if(x) x else y

or(true, false)
or(false, false)
or(false, true)
