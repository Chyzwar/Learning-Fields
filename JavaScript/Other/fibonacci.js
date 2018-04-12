 // (2^N) time complexity
function fibRecursive(n) {
  if (n < 2) {
    return 1;
  } else {
    return fibNaive(n - 2) + fibNaive(n - 1);
  }
}
//O(n) time complexity
function fibIterativeSimple(n) {
  let prev = 0;
  let curr = 1;
  let temp;

  while (n >= 0) {
    console.log(`n: ${n} prev: ${prev},  curr: ${curr}` )
    temp = curr;
    curr = curr + prev;
    prev = temp;
    n--;
  }
  return prev;
}

// O(n) time complexity
function fibIterativeSimple(n) {
  let prev = 0;
  let curr = 1;
  let temp;

  for(let i = 1; i <= n; i++) {
    console.log(`n: ${i} prev: ${prev},  curr: ${curr}` )
    temp = curr;
    curr = prev + curr;
    prev = temp
  }
  return curr;
}

// O(n) time complexity
function fibIterativeSeq(n){
  var fib = [0, 1];

  for(let i = 2; i <= n; i++){
    console.log(`n: ${i} prev: ${fib[i-2]},  curr: ${fib[i-1]}` )
    fib[i] = fib[i-2] + fib[i-1];
  }
  return fib;
}

