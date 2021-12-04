# A brief description of how our project is consistent with the SOLID design principles

## 1. Single Responsibility Principle
We allocate tasks into separate classes since we know the fact that the more responsibilities one class takes, the more risks we have while changing even a subtle part of the code. For example, we divide the entity into four different parts, `Card`, `Pack`, `User`, `ProgramState` which are matched by `CardManager`, `PackManager`, `UserManager`, and `ProgramStateManager` respectively in use case. Each part owns their simple responsibility with low dependency on others. If we alternate the code in one of them, others will not be affected under coupling reactions. Also, we followed the suggestion that TA gave in Phase 0: separate a `LogInOutManager` from `UserManager` to control user login.

## 2. closed/open principle
This emerged in several packages, which promotes the use of interfaces to enable us to adapt the functionality of application without changing the existing code. For example, we have a `Sort` interface that's shared among `PackManager` and `CardManager` to sort packs/ cards in date added and alphabetic order. We use interfaces instead of superclasses, closed for modifications, and providing new implementations to extend the functionality of our software.

## 3. Liskov Substitution Principle
In order to achieve this principle, we are supposed to confirm that objects of a superclass shall be replaceable with objects of its subclasses without breaking the application. That requires the objects of our subclasses to behave in the same way as the objects of our superclass. We have followed this principle as all of our subclasses, with an "is a" relationship between `CardManager` /`PackManager` /`UserManager` and their parent classes `Manager`. And in interface_adapter.gateway, all of `CardWriter`, `PackWriter`, and `UserWriter` "is a" `Writer`.

## 4. Interface Segregation Principle
We kept every input boundary small so that it doesn't contain methods that controller doesn't need. For example, `Sort` interface only contains method that sort by alphabetic order and date added order. These two methods can be shared when sorting packs and cards.

## 5. Dependency Inversion Principle
As we said in `clean architecture` file, we implemented `DataInOut` and its interface `IDataInOut` for dependency inversion, so that usecases who want to write objects into database will use `IDataInOut` instead of depending on interface_adapter.gateway directly.
