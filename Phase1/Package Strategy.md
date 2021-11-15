# Description of which packaging strategies we considered, which we decided to use, and why.

We considered to package our program by layers according to clean arcitecture principle.

We created several packages and main, which is `entity` in inner layer; `use_case` package, which consists of `input_boundries`, `output_boundries`, `Manager`,  `Generator` and `constants` as Use Case layer; `controller`,`presenter`,`gateways` as interface adapters; finally, in the frameworks layer, there are `GUI` and `command_line_interface` packages.

As a direct benefit of this packaging strategy in this way, our program will be high cohesion, high modularity, and low coupling between packages.
The package by feature allows some classes to decrease their scope from public to package-private, greatly reducing the need to navigate between directories.
In general, the overall size of the application is communicated by the number of packages, and the basic features are communicated by the package names.
