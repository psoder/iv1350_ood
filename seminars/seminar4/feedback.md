# Feedback

## 1. Exceptions are thrown when an error occurs
    a. All exceptions are catched
    b. Abstraction level is correct
    c. The exception is correctly named.
    d. Info is included.
    e. Java exceptions are used
    f. Exceptions are documented
    g. Objects are not mutated
    h. Users are notified.
    i. The logger should probably be in the controller and not the view.
    j. A lot of the tests are not implemented. The tests that tests exceptions should probably assert that an exceptions is thrown and not throw the exceptions itself.
    
## 2.1 
    a. Avoid using inheritance as much as possible, use interfaces instead.
    b. Why are observers added and removed all the time? Add the observers in the Main class instead. That way you only have to do it once.
    c. The observer does the correct thing, but in a very convoluted way.
    d. Why does the Controller have a list of observers?
    e. Why are the observers added to the Controller in the View class? This makes View depend on the observers.
    
## 2.2 The singleton pattern seems to be correctly implemented and used.

## 2.3 I'm impressed, you've somehow turned something that could be less than 50 lines to over a a thousand. I honestly have no idea if it's correcytly implemented since it's impossible to understand what's going on. It looks correct but idk.

## 3. Since I cant understand what youve done it's probably not sufficently well explained.

## 4. No, see 2.1

## 5. Through the Controller. The cohesion becomes a bit worse but the coupling stays the same.

## 6. Only the relevant data is passed (the new balance).

## 7. 
    a. The method explains how you worked when writing the program.
    b. The code is explained, altho some parts could be a bit more well expalined (e.g. the strategy/factory part)
    c. The discussion is a bit lackluster. However, it does contain some good insight into why the program is as it is. 
    My tips here would be to write propper tests for the exceptions, ditch the publisher class and the program could proably use a good rewrite to make it clear what is going on.
