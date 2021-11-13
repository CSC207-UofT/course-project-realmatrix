# A summary of design patterns our group has implemented (or plans to implement).

In general, we use Strategy design pattern in our project, specifically in PACKMANAGER. 
As we all know, Strategy pattern could encapsulate interface details in a base class and bury implementation details in derived classes. 
Clients can couple themselves into an interface and not have to experience the upheaval associated with change. 
With more pertinence, if we have multiple classes that differ only in their behaviors and these various algorithms should not be implemented within the class, 
under this circumstance, we are supposed to use the strategy pattern.
In our PACKMANAGER, we would like to sort the cards storing in the PACK in different kinds of ways, 
such as sorting by alphabet with AtoZ and ZtoA, by date with OldtoNew and NewtoOld.
Therefore, We create an interface called SORTER, as a strategy class, implemented by natural order and reverse order. 
And in the PACKMANAGER, what we will do is just to set the strategy and ask the strategy to sort cards. 
Users could select their preferred algorithm to sort cards through strategy pattern.
