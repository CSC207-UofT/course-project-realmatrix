# Progress Report

## Major Decision and Worked Well
Our major design decision in Phase 1 is that we use a program state to keep track of current `user`/`pack`/`card`.
In this way, while we are implementing our user interface, we can use setter and getter to display the correct element to user
Later, we might turn this into an observer pattern to make our design cleaner.


## Division of work summary
- Xing Ling worked on the database, readers and writers, polishing good design of these classes.
- Ziqi Shu worked on the all tests for entity classes , Manager Classes, UseCase Classes and Controller classes.
- Runshi Yang worked on the `LearnGenerator`, `LearningSystem`, `LearnController` and `LearnAndReviewDisplay` of the CLI.
- Yifan Zhao worked on all input boundaries of every usecase; wrote all controllers of each usecase, most of the command line interface display class, and implemented `reviewGenerator`; fixed some bugs in the usecase, currently working on writing Exceptions for different scenarios.
- Chloe Huang implemented `PackManager`, all Presenter and OutputBoudnary; modified all usecase classes and controllers to fit in presenter; implemented factory design pattern & data access interface in gateway and all of simple GUI with login/register page; wrote descriptions of design patterns our group has implemented and major design decisions. She will work on more of GUI in phase 2.
- Jiarun Cai implemented `Pack` and `PackManager`, reviewing `CardManager`; wrote descriptions of how our project adheres to Clean Architecture and be consistent with the SOLID design principles, and description of packaging strategies we considered; wrote progress report. She is considering to add strategy pattern in `PackManager`.

## open questions
The `write` methods in `gateway` can write an entity's attributes into database. For example, `CardWriter`'s `write` method takes in a `Card` object, and performs `card.getTerm()` and `card.getDefinition()` to write the card's term and definition separately into database. **We are not sure if it's clean for `gateway` to call on entity methods.**

