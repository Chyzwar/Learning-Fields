
// ------------------ ES5 --------------------//
/**
 * Constructor for superclas is called when extending prototype
 * Defining methods on prototype can only be done after proto is extened
 */
function Human(){
  console.log('Human constructor called');
}

Human.prototype.speak = function(){
  console.log('Human speak');
}

function Person(){
  console.log('Person constructor called');
}

Person.prototype = new Human();

Person.prototype.walk = function() {
  console.log('Person walk');
}

var person1 = new Person();
person1.speak();
person1.walk();

// ------------------- ES5 Object.create ------//
// Constructor of superclass is called using call(this, arg1, arg2)
// To avoid calling constructor again use Object.create on proptype of superclass
function Human(){
  console.log('Human constructor called');
}

Human.prototype.speak = function(){
  console.log('Human speak');
}

function Person(){
  Human.call();
  console.log('Person constructor called');
}

Person.prototype = Object.create(Human.prototype);
Person.prototype.constructor = Person;

Person.prototype.walk = function() {
  console.log('Person walk');
}

var person1 = new Person();
person1.speak();
person1.walk();
// ------------------- ES6 Sugar --------------//

class Human {
  constructor(){
    console.log('Human constructor called');
  }
  speak() {
    console.log('Human speak');
  }
}

class Person extends A {
  constructor(){
    super();
    console.log('Person constructor called');
  }
  walk(){
    console.log('Person walk');
  }
}

var person2 = new Person();
person2.speak();
person2.walk();
