# iv1350_ood

## The program

The code is located in [app/src/main/kotlin](app/src/main/kotlin) and the main
file is [Main.kt](app/src/main/kotlin/Main.kt)

The tests are located in [app/src/test/kotlin](app/src/main/kotlin)

### Kotlin Crash Course

- functions are declared with `fun` and are public by default. Return type is
    specified with the `:`, default values (prevents having to overload everything.)
    are declared with `=`. A sum function `fun sum(a: Int, b: Int = 0): Int` where
    `b` is `0` by default and the return type is `Int`.

- `val` is like `final` in Java

- `var` declares a variable and infers it's value. If the value can't be infered
    `: T` is used to declare the type of a variable, e.g. `var a: Double` is 
    equal to `Double a` in Java

- `T?`means that the variable of type `T` is nullable. A variable of type `T`
    can not be null.

- `?.` is the safe call operator and when it's used the resulting object is guaranteed
    to not be null. E.g. `a?.length` returns `a.length` if `a` is not null, and
    null otherwise.

- `?:` is the Elvis operator and works like a tenary operator except with null.
    E.g. if we want to assign `b.length` to `a` iff `b.length` is not null we can
    do `var a: Int = b?.length ?: /* default value or throw exception */`

- `require(condition){msg}` throws an exception if the condition isn't true

- There is no package private modifier in Kotlin

- Exceptions are not checked, i.e. you have to explicitly catch them and don't have to
    surround all method calls to exception throwing methods with a try-catch

### Other good-to-know things

- `fold` is an operation that does a somthing to all elements and accumulates
    the value into an accumulator. To sum the numbers between 1 and 10 we write,
    `listOf(1..10).fold(0){sum, currentValue -> sum + currentValue}`


### Dependencies

- Java 16

### Running the app

#### On Linux

```sh
# To run
./gradlew run

# To test
./gradlew test

# To test and run 
./gradlew test && ./gradlew run
```

#### On Windows

```
# To run
gradlew.bat run

# To test
gradlew.bat test
```

## Länkar

- [Kursbok](https://leiflindback.se/iv1350/object-oriented-development.pdf)
- [Canvas](https://canvas.kth.se/courses/31178)
- [Seminarier](https://canvas.kth.se/courses/31178/pages/seminar-tasks)

## Seminarier

### Seminarie 1

- [Uppgiftsbeskrivning](https://canvas.kth.se/courses/31178/files/5271371/download?wrap=1)
- [Kriterier](https://canvas.kth.se/courses/31178/files/5235478/download?wrap=1)

### Seminarie 2

- [Uppgiftsbeskrivning](https://canvas.kth.se/courses/31178/files/5271372/download?wrap=1)
- [Kriterier](https://canvas.kth.se/courses/31178/files/5235479/download?wrap=1)

### Seminarie 3

- [Uppgiftsbeskrivning](https://canvas.kth.se/courses/31178/files/5271373/download?wrap=1)
- [Kriterier](https://canvas.kth.se/courses/31178/files/5235476/download?wrap=1)

### Seminarie 4

- [Uppgiftsbeskrivning](https://canvas.kth.se/courses/31178/files/5271374/download?wrap=1)
- [Kriterier](https://canvas.kth.se/courses/31178/files/5235477/download?wrap=1)
