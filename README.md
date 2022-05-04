# iv1350_ood

## The Program

The code is located in [app/src/main/kotlin](app/src/main/kotlin) and the main
file is [Main.kt](app/src/main/kotlin/Main.kt).

The tests are located in [app/src/test/kotlin](app/src/test/kotlin).

### Dependencies

- Java 11 (or higher)

### Running the app

#### In sh/bash/zsh/fish

```sh
# To run
./gradlew --console=plain run

# To test
./gradlew test

# To test and run 
./gradlew test && ./gradlew --console=plain run
```

#### On Windows

```bat
# To run
gradlew.bat --console=plain run

# To test
gradlew.bat test
```

### Kotlin Crash Course

- functions are declared with `fun` and are public by default. Return type is
    specified with the `:`, default values (prevents having to overload everything.)
    are declared with `=`. A sum function `fun sum(a: Int, b: Int = 0): Int` where
    `b` is `0` by default and the return type is `Int`.

- Named arguments can be used to make it easier to read  

- `val` declares an immutable variable, comparable to `final` in Java.

- `var` declares a variable and infers it's value. If the value can't be infered
    `: T` is used to declare the type of a variable, e.g. `var a: Double` is
    equal to `Double a` in Java. `var b = 2` is equal to `Int b = 2` in Java.

- `T?`means that the variable of type `T` is nullable. A variable of type `T`
    can not be null.

- `?.` is the safe call operator and when it's used the resulting object is guaranteed
    to not be null. E.g. `a?.length` returns `a.length` if `a` is not null, and
    null otherwise.

- `?:` is the Elvis operator and works like a tenary operator except with null.
    E.g. if we want to assign `b.length` to `a` iff `b.length` is not null we can
    do `var a: Int = b?.length ?: /* default value or throw exception */`.

- `require(condition){msg}` throws an exception if the condition isn't true.

- There is no package private modifier in Kotlin.

- Exceptions are unchecked, i.e. you have to explicitly catch them and don't
    have to surround all method calls to exception throwing methods with a try-catch.

- Singletons are declared with the `object` keyword. E.g. a singleton would be
    defined with `object Singleton{}`.

- When using lambda expressions instead of writing `list.forEach(item -> println(item))`
    `it` can be used to refer to the current element simplifying to `list.forEach(println(it))`.

### Other good-to-know things

- `fold` is an operation that does a somthing to all elements and accumulates
    the value into an accumulator. To sum the numbers between 1 and 10 we write,
    `Array(10){i -> i + 1}.fold(0){sum, value -> sum + value}`
    (`Array(10){i -> i + 1}` creates the array). In this case reduce could have
    been used since the list type is the same as the resulting value, but this
    is not the case in the actuall program.

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
