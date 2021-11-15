# Description of which packaging strategies we considered, which we decided to use, and why.

We considered to package our program by layers according to clean arcitecture principle and feature.

We created 10 packages and main, which is `entity` as inner layer; `use_case` as Use Case layer; `controller`,`presenter`,`interface_adapter.gateway` as interface adapters respectively. In the frameworks, there are `framework.command_line_interface.GUI`,`framework.command_line_interface.constants`, `input_boundries`, `output_boundries` and `framework.command_line_interface`, seperated by feature.

As a direct benefit of this packaging strategy in this way, our program will be high cohesion, high modularity, and low coupling between packages.
The package by feature allows some classes to decrease their scope from public to package-private, greatly reducing the need to navigate between directories.
In general, the overall size of the application is communicated by the number of packages, and the basic features are communicated by the package names.
