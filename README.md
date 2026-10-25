# OC2 

## Attribution

Original Repo: https://github.com/emilybache/Theatrical-Players-Refactoring-Kata

Caden Ebert
10/25/2026

This is a refactor of the java code. My refactor adds seperate classes for calculating charge and
calculating volume credits. It also simplifies creating the result string by using string builder instead.
I also added 3 tests for the case of no performaces, a tradegy play with 21 audience members, and a historical with 21 audience members.

## Technology used

- Java 22
- Gradle Wrapper 9.1.0
- JUnit Jupiter 6.0.0
- ApprovalTests 25.5.0
- AssertJ 3.27.6

``` bash
cd java
```

Run tests

```bash
./gradlew clean test

```

## Initial & Final Test Results

I added four tests:

- An empty invoice with no performances.
- A tragedy with 31 audience members.
- A historical play with 21 audience members.
- A historical play with 35 audience members.

Initial result:

Initially there was only 2 tests and both passed initially. 
The tests originally only covered the rejection of unsupported play types and an example statement for
comedy and tragic plays.

Final result:

Finally there are 6 tests with all 6 passing. This includes the initial 2 tests but also has 4 new tests that 
each test for something specific. The first test I added was a test that passes 0 performaces and expects and empty envoice with 0 credits. I added 1 edge case test for a historical play with 21 audience members that tests the surcharge above 20 and 2 tests that verify 2 examples for 31 audience members for a tradegy and 35 audience members for a historical play.

## Refactoring


1. Extracted charge calculation

I moved the tragedy and comedy pricing logic from StatementPrinter into a new ChargeCalculator class. This improved the design by giving pricing its own focused responsibility instead of mixing it in with StatementPrinter. I ran the original test suite after this change, and both original tests passed.

2. Extracted volume-credit calculation

I moved the volume-credit rules into a new VolumeCreditCalculator class. This separated credit calculations from pricing and output generation, making each class easier to understand and extend later down the line. I ran the original tests again, and both tests passed.

3. Replaced string concatenation with StringBuilder

I changed StatementPrinter to build the statement with StringBuilder instead of repeatedly using a string variable. This makes the creation of the result string clearer. The original tests passed again.

4. Historical play support

Historical play support is added and implements more funcionality. Hisotrical plays have a base charge of $500 with an added $15 for each audience memeber over 20.



## Reflection

A reflection of approximately 300–500 words addressing: Which refactoring most improved the
design, and why? How is refactoring different from adding functionality? How did the new design
help (or not help) you add historical plays? Which of the original maintainability issues were
resolved?

The refactoring that improved the design the most is the seperation of charge and volume calculation. It will make adding support for more plays down the line easier and more efficient. It also makes the code a lot easier to parse as StatementPrinter isnt as clouded with a bunch of code that is all doing different things. If I had to pick just one refactor that helped the most I think I would pick the charge calculation being seperated into a seperate function. It was a huge part of the original StatementPrinter and it handles most of the functionality of making a statement. Giving it a seperate class creates makes it way more obvious what the responsibility of the code is. Also, future support for different plays have a more obvious location and takes some responsibility off of the StatementPrinter.

This is a great example of how refactoring is different from adding functionality as no extra functions were adding with these changes but it does a lot to make it easier to add functionality down the road. Refactoring can be entirely just making changes to the code for the sole reasong of organization. It is more of changing the internal structure and not changing the observable behavior on the outside.

It made it easier to add historical play support because I knew exactly what needed to change and where. Giving a specific place to charge and volume credit calculator tells me I likely need to add code into both of these files in order to add support for historical plays as it marks a clear step in the process of creating a statement.

The original maintainability issues that were resolved include the clouded StatementPrinter function, mixed pricing and string building responsibilities, repeated string additions, unclear variable names, and calculations directly in the main function.  
