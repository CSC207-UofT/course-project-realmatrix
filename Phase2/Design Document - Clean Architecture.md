# A brief description of how our project adheres to Clean Architecture


In order to render our project to comply with Clean Architecture, we organize our code in
- 4 entity classes;
- 8 use case classes (5 Managers and 3 learn/review generator classes);
- 8 controllers (each manages a specific functionality);
- 1 gateway package that contains loader/writer classes as well as data access interface;
- 9 specific `Presenter` classes;
- 1 GUI + 1 Database.

### What makes our code clean
#### Two main aspects：
1. Implementation of `InputBoundary` interface and `OutputBoundary` interface.
    - Having these two boundaries **reduces dependency**: controllers won't directly make use of specific usecase classes;
   usecase class won't directly make use of specific presenters.


2. Data access interface `IDataInOut` follows dependency inversion. In this way, when usecase classes saves/load data, 
they won't interact with concrete data writer/loader classes.

#### To talk about different layers in more details:
1. Entity layer is completely independent.

    - As the center of the architecture, the domain layer known as entity, consist of three package, which each are 
   `Card`, `Pack`, `User`, `ProgramState`. `User` stores a username, a password, and a list of packages the user created.
   `Pack` stores a name of pack, and a list of Cards in the pack. `Card` stores a term, a definition, and a proficiency index 
   (how well the user masters the card). `ProgramState` stores the current user, pack, and card. Each of these
   three classes neither have any knowledge of the other layers nor dependent on other components residing in the outer layers.


2. Usecase Layer: it contains two parts, `Manager` and `Generator`, by which present what we can do with the entity in pure
business logic and plain code.
    - Managers: `CardManager`, `PackManager`, and `UserManager`, take charge of `Card`, `Pack` and `User` respectively,
   including creating, editing, sorting, searching and storing into a map.
   There is also an abstract called `Manager` that contains methods/attributes that are shared between specific managers.
   `ProgramStateManager` is a manager that manages to update current card/pack/user.
   **Throughout the program, there will only be one such manager.**

    - `TaskGenerator`: `LearnGenerator` and `ReviewGenerator`. Their major goal is to generate a list of cards for learning/reviewing,
   and tell the learn/review presenters to present via output boundaries.


3. Interface Adapters Layer:
   - Take Controllers for an example, we set multiple controllers, **each controls a specific task**
   (e.g. `RegisterController` controls registration).
   These controllers will be 
   getting input from user command and
   instructing the usecase classes 
   (but won't depend on usecase classes directly because of input boundaries).

4. Framework & Driver Layer:
   - Take GUI for an example, The GUI we built in phase2 only interact with the Interface Adapters Layer,
   and will not dependent on lower layers, which satisfies the clean architecture principle.

