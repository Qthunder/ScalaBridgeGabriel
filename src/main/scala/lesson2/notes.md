# ScalaBridge - Lesson 2 Summary

## Reading Revision Chapters 1-2 & Exercise:

Core Concepts:
- Relationship between values, expressions, types
- *Every* expression has a *compile-time* type and a *run-time* value.
- Scala is a statically typed language, but has type inference. Important to understand what each of them are and why this is not contradictory.

Other Exercise take-aways:

(Except for the first 2 or 3, not super-important)

- Type inference is neat, but don't hesitate to explicitly specify the types when needed.
- The `Unit` type, used as a return type for functions that don't "logically" have a return value, but are called for their side-effect.
- Types are completely oblivious of (run-time) values. The type of `0/0`, for instance, is `Int` - the fact that this will throw an exception at run-time is irrelevant.
- Block expressions - just like methods, values can be declared as a block, where the value of the block is the value of its last line.
- The compiler will, while giving a warning, infer `Unit` in certain situations: Empty block, block ending with a declaration instead of an expression, a missing else clause, etc.
- Scala uses Java primitive types, which sometimes will be quietly converted in both expected and surprising ways.
- Fields declared with `val` are immutable, where as those declared with `var` can be modified. As functional programmers we favor immutability, which gives us local reasoning.
- Any, AnyRef, AnyVal sit at top of the type hierarchy. No need to remember details (they're in the book), but good to know in case they show up in some error message. That will usually happen when you've mistakenly unified two unrelated types.
- The compiler will, while giving a warning infer "Unit" in certain situations: Empty block, block ending with a declaration instead of an expression, a missing else clause.

## Algebraic Data Types

Core concepts:
 - Modelling data using logical ands and ors
 - Representing data as precisely as possibly. Invalid data should be *impossible* to construct.

### Or Types:

Example : A day of the week **is** a `Monday` *or* a `Tuesday` *or* a `Wednesday` etc..

```scala
sealed trait Day
case object Monday extends Day
case object Tuesday extends Day
.
.
.
```

To instantiate values of type `Day` simply refer to the object directly: 

```scala 
val monday : day = Monday
```

- Note: `sealed` denotes that the type can not be extended outside of the file. This allows the compiler to give us certain guarantees when `pattern matching` (see below)
 This is orthogonal to the familiar Java-like `access modifiers` such as `private`, `public` etc.
  
### And Types

Example: A student **has** a name, **and** an age **and** a grade.

```scala
final case class Student(name: String, age: Int, grade: Int)
```
Instantiate case classes like you would regular classes, but without the `new` keyword

```scala
val me : Student = Student("Gabriel", 25, 2)
```


- Note: (If you're familiar with Java classes) Case classes *are* classes, but with slightly different behaviours. Importantly, equality checks will look at *structural equality* (i.e equality of all its fields) rather than *reference equality* - as regular classes. Don't worry if you don't know what that means.

### Combine them to obtain more complex types  

Example : A StudentGrade score **and** rank, **or** "Absent"

```scala
sealed trait StudentGrade
  final case class Grade(score: Int, rank: Int) extends StudentGrade
  case object Absent extends StudentGrade
```

## Pattern Matching

Syntax: 
```scala 
expr0 match {
 case <case_object_identifier_1> => expr1
 case <case_object_identifier_2> => expr2
 case <case_class_identifier>(argument_binding_1, argument_binding_2) => expr2 ...
 .
 .
 .
}
```

Example:


```scala 
def passStudent(studentGrade: StudentGrade) : Boolean = {
    studentGrade match {
      case Grade(score, rank) => score > 50
      case Absent => false
    }
  }
```

Notes
- The `case` keywords used in the match mirror those used in the type declaration - that's (part of) why they're there!
- The compiler will warn you if a match is non-exhaustive. Thanks to `sealed` keyword above.
- Pattern matching is a common FP (functional-programming) feature. It allows us to follow the FP principle of separating data from its behaviour.
- The pattern match is itself an expression, with a compile-time type and a run-time value. Thus it can be used wherever an expression is expected 
- `expr 0`, `expr1`, `expr2` can be any expression - not just the simple ones we used. This includes blocks, method calls, or even another pattern match!
- This isn't a complete demonstration of the possibilities of the pattern matching syntax, but will do for now.

## Card example

```scala

sealed trait Card

final case class RegularCard(suit: Suit, rank: Rank) extends Card
case object Joker extends Card

sealed trait Suit
case object Hearts extends Suit	
case object Spades extends Suit
case object Diamonds extends Suit
case object Clubs extends Suit

sealed trait Rank
case object Ace extends Rank
case object King extends Rank
case object Queen extends Rank
case object Jack extends Rank
case object Ten extends Rank
case object Nine extends Rank
case object Eight extends Rank
case object Seven extends Rank
case object Six extends Rank
case object Five extends Rank
case object Four extends Rank
case object Three extends Rank
case object Two extends Rank
```

## Glossary/Jargon

Some FP-related terminology. Useful to know because

 a) It will be used in the "literature" 
 
 b) To sound smart.
 
 c) You might want to refer to some of these concepts in a language-agnostic way
 
 d) Understand what I'm blabbering about in the future
 

- `Algebraic Data Type` : Data modelled using logical ands and ors
- `Sum Type`: Another way of referring to `And` types (Intuitive reason: The number of possible values of a `sealed trait` is the **sum** of the number of possible values of the types that extend it )
- `Product Type`: Another way of referring to `Or` types (Intuitive reason: The number of possible values of a `case class` is the **product** of the number of possible values of the types of its members)
- `Pure` / `Referentially transparent`: A function or a piece of code is said to be pure or referentially transparent if executing it doesn't change the state of the outside world.
- `Impure` / `Side-effectful`: The counterpart to pure code. Executing an impure function will change some state of the world. Performing any kind of I/O falls in this category

## For next time:

- Make sure you understand everything in this lesson.
- Read chapters 3,4 from Essential Scala
- Play with the doodle-bot. Start work on your own project by modeling some domain types you expect to need - using what we learned today.
 

