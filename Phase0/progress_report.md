# Progress report

## Specification summary

Our program is to create a platform where users can use flashcards to memorize pieces of knowledge.
Users can either **learn** or **review** cards they have created. 
In both modes, the frequency that a card appears will be dependent on the proficiency index (how well the user has mastered this card),
and their learning/reviewing performance this time will in turn updates the proficiency index.

## CRC model summary

- 3 entity classes: `Card`, `Pack`, and `User`.
- 4 usecase classes: `CardManager`, `PackManager`, `UserManager`, and `TestGenerator` (generates learning/reviewing mode).
- 1 controller: `System` that gets input from user command and instructs the usecase classes.
- 1 command line interface: `CommandLineInterface`

## Scenario walk-through summary

We describe the scenario where a user can 
- create an account,
- log in to the platform, 
- view his/her existing packs, or create a new pack,
- view cards in a pack, or create a card that contains a term and its definition,
- learn cards,
- review cards repeatedly.

## Skeleton program summary

The skeleton program includes all classes listed in CRC model except `System`.

- Under `src/main/java`:
  - Package `entity` includes `Card`, `Pack`, and `User`.
  - Package `manager` includes `CardManager`, `PackManager` and `UserManager`.
  - Package `use_case` includes `TestGenerator`.
  - Package `main` includes `CommandLineInterface`.


- Under `src/test/java`
  - Package `test` includes `CardTest`.

## Division of work summary

Chloe worked on `Card`, `CardManager`, and `CardTest`, reviewed and suggested ideas for `User` and `UserManager`, and wrote the final version of specification.

Jerrina worked on `PackList` and`PackManager`, wrote the second version of walk-through scenario.

Xing Ling implemented class `Manager`, `UserManager`, `User`; reviewed `Card`

Yifan worked on `TestGenerator`, and wrote drafts for specification/CRC model/scenario walk-through, and a simple command line interface.

Ziqi worked on `Card` and `CardManager` and wrote the last version of senario.

Runshi Yang Worked on `Pack` and reviewed `PackManager` and `PackList`.

## Our strengths so far

In general, we followed the clean architecture design principle by having clear layers of entity, usecase and controller classes,
so that our skeleton program is easy to navigate and modify in the future.

We assigned a unique ID to each user/pack/card, so if we want to store all user/pack/card information in a database
in the future, we can easily access each of them.

## Question

Since Pack stores a list of Cards, should we use PackManager to manage both Pack and Cards (i.e. PackManager can modify cards) 
or do we use a CardManager to separately manage cards? (Solved during last meeting with TA, we'll change our implementation next week).



