# A brief description of how our project adheres to Clean Architecture


<p>In order to render our project to comply with Clean Architecture, we organize our code in three entity classes, four use case, one controller and one command line interface. </p>

<p>As the center of the architecture, the domain layer known as entity, consist of three package, which each are CARD, PACK and USER. USER stores an ID, a username, a password, and a list of packages the user created. PACK stores an ID, a name of pack, and a list of Cards in the pack. CARD stores an ID, a term, a definition, and a proficiency index (how well the user masters the card) of the card. Each of these three classes does not neither have any knowledge of the other layers nor dependent on other components residing in the outer layers.</p>

<p>We divide the use case into two parts, use_case.use_case.manager and generator, by which present what we can do with the entity in pure business logic and plain code. In use_case.use_case.manager, CARDMANAGER, PACKMANAGER, and USERMANAGER, take charge of CARD, PACK and USER respectively, including creating, editing, and storing a map. Furthermore, there is an interface called SORT creating a method to sort cards by several distinguishing ways and an abstract called MANAGER returning a unique identifier of 10 characters long. The major goal of generator is to check if card is learnable or reviewable by TESTGENERATOR and raise a collection of cards for user to learn and review by REVIEWGENERATOR. All of these do not have to know who triggers them and how the results will be presented.</p>

<p>In adapter layer, we set LEARNINGSYSTEM as our controller, getting input from user command and instructing the use case classes. This layer shoulders the responsibility of converting user inputs into the request model and passes to the use case, but have no access to views in the framework circle.
Eventually, we create database in the infrastructure layer and a command line interface in main.</p>
