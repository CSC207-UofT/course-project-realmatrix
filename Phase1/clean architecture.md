# A brief description of how our project adheres to Clean Architecture


In order to render our project to comply with Clean Architecture, we organize our code in 
- 3 entity classes; 
- 8 use case classes (5 Managers and 3 learn/review generator classes);
- 8 controllers;
- 1 `gateway` package that contains loader/writer classes as well as data access interface
- 6 specific `Presenter` class (more will be added in phase 2)
- 1 complete command line interface (which will be replaced by GUI in phase 2)
- 1 currently-implementing GUI

### What makes our code clean
##### Two main aspects：
1. Implementation of `InputBoudnary` interface and `OutputBoudnary` interface. 
   - Having these two boundaries **reduces dependency**: controllers won't directly make use of specific usecase classes; usecase class won't directly make use of specific presenters.


2. Data access interface `IDataInOut` follows dependency inversion. In this way, when usecase classes saves/load data, they won't interact with concrete data writer/loader classes. 
   - but `gateway` has an unclean design currently: the load method which loads all users (username and password) to check login  returns a `UserManager` object which contains a hashmap of all user id to user. Thus, `gateway` is not only loading data, but also adding all users into a usecase class and return it. This code flows between layers. We will solve it in phase 2

#### To talk about different layers in more details:
3. Entity layer is completely independent. 

   - As the center of the architecture, the domain layer known as entity, consist of three package, which each are `Card`, `Pack` and `User`. `User` stores an ID, a username, a password, and a list of packages the user created. `Pack` stores an ID, a name of pack, and a list of Cards in the pack. `Card` stores an ID, a term, a definition, and a proficiency index (how well the user masters the card) of the card. Each of these three classes neither have any knowledge of the other layers nor dependent on other components residing in the outer layers.


4. Usecase Layer: it contains two parts, `Manager` and `Generator`, by which present what we can do with the entity in pure business logic and plain code.
    - Managers: `CardManager`, `PackManager`, and `UserManager`, take charge of `Card`, `Pack` and `User` respectively, including creating, editing, sorting, searching and storing into a map. Furthermore, there is an interface called `Sort` creating a method to sort cards by several distinguishing ways. This interface will be implemented in `UserManager` to sort pack in future. There is also an abstract called `Manager` returning a unique identifier.

    - `TaskGenerator`: `LearnGenerator` and `ReviewGenerator`. Their major goal is to check if card is learnable or reviewable and raise a collection of cards for user to learn or review. All of these do not have to know who triggers them and who will present the result (benefit of OutputBoundary).


5. Controllers: we set multiple controllers, **each controls a specific task** (e.g. `RegisterController` controls registration). These controllers will be getting input from user command and instructing the usecase classes (but won't depend on usecase classes directly because of input boundaries). This layer shoulders the responsibility of converting user inputs into the request model and passes to the use case, but have no access to views in the framework circle.

