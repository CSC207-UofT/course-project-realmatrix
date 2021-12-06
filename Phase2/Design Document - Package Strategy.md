# Description of which packaging strategies we considered, which we decided to use, and why.

We considered packaging the classes in our project by layers according to the clean architecture principle.

We created several packages and main, which are 
`entity` package consisting all the entities in the inner layer;
`use_case` package, which includes `input_boundries`, `output_boundries`, `manager`,  `generator` and `constants` lying in the use case layer;
`interface_adapter` package involving `controller`,`presenter`,`gateways` as interface adapters;
finally, in the frameworks layer, there is a `framework.gui` package.

As a direct benefit of this packaging strategy, our program will be high cohesion, high modularity, and low coupling between packages.
Packaging by layer allows some classes to decrease their scope from public to package-private, greatly reducing the need to navigate between directories.
In general, the overall size of the application is communicated by the number of packages, and the basic features are communicated by the package names.
