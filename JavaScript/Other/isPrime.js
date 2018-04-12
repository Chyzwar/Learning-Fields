
// O(n)
function isPrime(num) {
  for(let i = 2; i < num; i++) {
    if(num % i === 0){
      return false;
    }
  }
  return num > 1;
}

// O(sqrt(n))
function isPrime(num){
 for(let i = 2, sqrt = Math.sqrt(num); i <= sqrt; i++) {
    if(num % i === 0) {
      return false;
    }
 }
 return num > 1;
}
