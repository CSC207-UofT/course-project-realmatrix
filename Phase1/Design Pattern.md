# A summary of design patterns our group has implemented (or plans to implement).

### Already implemented: _Factory Design Pattern_
This design pattern is implemented in `interface_adapter.gateway`. Some background about our `Writer` class which write data into database:  we have different `Writer` class for writing different entity (e.g. `CardWriter` to write a `Card` object into database). 

Thus, if we do not implement Factory Design Pattern, then every time we want to write an entity into the database, we have to decide which writer class should be instantiated. This causes high-coupling problems.

To solve the problem, we implement Factory Design Pattern. This pattern takes in the entity we want to write into database, and decides which specific `Writer` class should be instantiated. In this way, we put all creation of `Writer` class in one place. Other classes don't need to decide instantiating which specific `Writer` class. Also, if we want to add more entities (thus more writer classes) in the future, it can be easily extended.  

### Plan to implement: _Observer Design Pattern_
We plan to use this design pattern to update status in `ProgramState`. For example, if `LogInOutManager` successfully signs in a user, it will trigger `ProgramState` to update its `user` attribute from `null` to the user that signs in.

### Plan to implement: _Template Method Design Pattern
We plan to use this design pattern to simplify the methods in the Controller and/or the generator packages. Since learn and review process are pretty similar, the process of implementing 
