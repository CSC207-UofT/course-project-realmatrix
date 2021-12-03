# major design decision

## 1. Program State
Our major design decision is that we use `ProgramState` to store current user/pack/card, and `ProgramStateManager` (concrete class for `ProgramStateInputBoundary`) to update the current entity.

In this way, while we are implementing our user interface, we can use setter and getter to display the correct element to user.

### 1.1 Why we didn't use _Observer Design Pattern_:
Initially, we decide to use `Observer` design pattern for changing current entity, like when a user signs in, `ProgramStateManager` will be triggered to update current user.

However, when we are implementing GUI, we realize we need to update current pack when a user goes into a pack, or update current card when a user edits a card. Therefore, it's the user action (which is from the UI layer) that triggers updates in program state. If we implement `Observer`, `ProgramStateManager` will observe behavior in GUI layer (user input), which violates clean architecture principle.

Therefore, instead of implementing `Observer` design pattern, we simply use `ProgramStateController` to get inputs from user and calls `ProgramStateManager` to update program state.

### 1.2 Why we didn't use _Singleton Design Pattern_:
Initially, we decide to apply this design pattern on `ProgramState`. However, after talking to professor Jonathan and searching about criticism of this design pattern, we decide to pass around a `ProgramStateManager` instead of using singleton design pattern to create a global instance that can be accessed anywhere.

The main reason is that "a singleton can be accessed anywhere by anything without having to use any parameters, **any dependencies would not be immediately visible to developers**. Consequently, developers would need to know 'the inner workings of code to properly use it' " from wikipedia. We believe hiding dependency is not good when working in a team, since other group members won't know how things are managed when just looking at a class from a high-level view.

Therefore, we prefer to pass around a `ProgramStateManager` to clearly indicate dependency.

## 2. Several writer classes, but only one loader class
In gateway, we decide to implement specific writer classes for each entity (like `CardWriter`, `PackWriter`, `UserWriter`), whereas we only have one loader class.

The reason is that we decide to immediately write into database when user adds/edits/deletes an entity (instead of writing all changes when the user quits the program). Therefore, it would be more reasonable to have a specific writer class for each entity, so that when a user manipulates one entity, we can use that specific writer class to manage the work.

Yet, for loading, we only load things at the start of our program (when user signs in/up), which is a one-time work. Therefore, we only implement one loader class.