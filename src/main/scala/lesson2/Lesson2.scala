package lesson2

object Lesson2 extends App {

  //Exercise 1 : What are the types of the following.
  class A
  object B

  val a: Int = 74
  val b: Unit = println()  // <---- Type "Unit", used as a return type for functions that don't "logically" have a return value, but are called for their side-effect.
  val example: Unit = () // <---- The "Unit" type has only one value - "()". It's not very interesting, as unit-returning functions are called for their side-effects.
  val c: Int = 0 / 0 // <---- The fact that this will blow-up at *run-time* has no impact on its *compile-time* type.
  val d: String = "tnellecxe".reverse // <--- Method call - unlike other languages, argument-less methods can be executed without an empty parameter list "()"

  val e: A = new A // The "class" key-word declares a type, "new" is used to crate values of that type

  val f: String = "he" concat "llo" // Method call. Observe the infix notation, which we usually expect from operators.
  val g: Int = 7.+(6) // Operators themselves aren't special. Like any (one argument) method, they can be called with either infix notation, or dot notation.

  val h: Boolean = true // Standard Booleans. Lower-case!
  val i: Int = 56
  val j: Int = {  // Block declarations: Just like methods, values can be declared as a block where the value of the block is the value of its last line.
    val y = 6
    9
  }
  val k = B
  val l: String = {
    val a = "This is a moderately long sentence of nine words"
    val b = 6
    a take b
  }

  // Any, AnyRef, AnyVal sit at top of the type hierarchy. No need to remember details (they're in the book), but good to know in case they show up in some error message.
  // They'll usually appear when in situations like below, you've (mistakenly) tried to unify two unrelated types.
  val m : Any = if (27 * 33 > 29 * 30) "Cat" else 'D'
  val n: AnyRef = if (true) new A else B
  val o: AnyVal = if (false) 6 else true

  // The compiler will, while giving a warning infer "Unit" in certain situations: Empty block, block ending with a declaration instead of an expression, a missing else clause.
  val q: Unit = {}
  val q2 : Unit = {
    val t =6
  }
  val p: AnyVal = if ("This sentence is so long".length > 19) 42

  // Recursive definitions - types can't be inferred
  val r: Int = 1 + r

  // Primitive type shannenigans. Scala uses Java primitive types, which sometimes will be quietly converted in both expected and surprising ways.
  // Again, something to keep in mind but not to worry too much about.
  val x: Int = '6' * 7
  val z = if (5 < 6) 25L else 5D

  // Difference between "val" and "var" - which to favor?
  // fields declared with "val' are immutable, where as those declared with "var" can be modified.
  // As functional programmers we favor immutability when possible, as it gives *local reasoning* which reduces certain kinds of complexity.

  // Difference between member 'a' and 'b' in object P?

  object P {
    val a = { println("Hello"); 22 * 15 }
    def b =  { println("Hello"); 22 * 15 }
  }

  val tripleHello = P.a + P.a + P.a
  val oneHello = P.b + P.b + P.b

  sealed trait DayOfTheWeek
  case object Monday extends DayOfTheWeek
  case object Tuesday extends DayOfTheWeek
  case object Wednesday extends DayOfTheWeek
  case object Thursday extends DayOfTheWeek
  case object Friday extends DayOfTheWeek

  val aDay : String = "Monday" // Unsafe, could put anything in there
  val theDay: DayOfTheWeek = Monday // The Scala way

  def nextDay(day: DayOfTheWeek) : DayOfTheWeek  = {
    day match {
      case Monday => Tuesday
      case Monday => Wednesday // Unreachable code - compiler warning
      case Tuesday => Wednesday
      case Wednesday => Thursday
      case Thursday => Friday
      // Missing Friday case - compiler non-exhaustiveness warning
    }

  }

  sealed trait StudentGrade
  final case class Grade(score: Int, rank: Int) extends StudentGrade
  case object Absent extends StudentGrade

  def passStudent(studentGrade: StudentGrade) : Boolean = {
    studentGrade match {
      case Grade(score, rank) => score > 50
      case Absent => false
    }
  }

  final case class Student(name: String, age: Int, grade: Int)

  def print(student: Student) : Unit = {
      student match {
        case Student(n, a, grade) => println(s"Name: $n, age:$a")
      }
  }


  val day : DayOfTheWeek = Monday






  // Or Types | Sum Types
  // And Types | Product Types


}
