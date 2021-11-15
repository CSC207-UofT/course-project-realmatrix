# A brief description of how our project is consistent with the SOLID design principles 

## 1. Single Responsibility Principle
 We allocate tasks into separate classes since we know the fact that the more responsibilities one class takes, the more risks we have while changing even a subtle part of the code. For example, we divide the entity into three different parts, `Card`, `Pack` and `User` which are matched by `CardManager`, `PackManager` and `UserManager` respectively in use case. Each part owns their simple responsibility with low dependency on others. If we alternate the code in one of them, others will not be affected under coupling reactions. Also, we followed the suggestion that TA gave in Phase 0: separate a `LogInOutManager` from `UserManager` to control user login. 

## 2. closed/open principle
This emerged in several packages, which promotes the use of interfaces to enable us to adapt the functionality of application without changing the existing code. Take `PackManager` as an example, we create an interface called `Sort`, providing with various ways of sorting item, such as in alphabet order or in date added order. As a result, we could implement `Sort` in `PackManager` to sort pack in what way users prefer to without switching the original code. We use interfaces instead of superclasses, closed for modifications, and providing new implementations to extend the functionality of our software. Also, `Sort` is useful because it will be implemented in `UserManager` to sort packs in phase 2.

## 3. Liskov Substitution Principle
In order to achieve this principle, we are supposed to confirm that objects of a superclass shall be replaceable with objects of its subclasses without breaking the application. That requires the objects of our subclasses to behave in the same way as the objects of our superclass. We have followed this principle as all of our subclasses, with an "is a" relationship between `CardManager` /`PackManager` /`UserManager` and their parent classes `Manager`. And in interface_adapter.gateway, all of `CardWriter`, `PackWriter`, and `UserWriter` "is a" `Writer`.

## 4. Interface Segregation Principle
We kept every input boundary small so that it doesn't contain methods that controller doesn't need. 

## 5. Dependency Inversion Principle
As we said in `clean architecture` file, we implemented `DataInOut` and its interface `IDataInOut` for dependency inversion, so that usecases who want to write objects into database will use `IDataInOut` instead of depending on interface_adapter.gateway directly.
