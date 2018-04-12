function fibNaive(n) {
  if (n < 2) {
    return 1;
  } else {
    return fibNaive(n - 2) + fibNaive(n - 1);
  }
}

function fibIterativeSimple(n) {
  let a = 1;
  let b = 0;
  let temp;

  while (n--) {
    temp = a;
    a = a + b;
    b = temp;
    n--;
  }
  return b;
}
