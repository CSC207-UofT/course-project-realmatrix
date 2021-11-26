# major design decision
- Our major design decision is that we use a program state to keep track of current user/pack/card
- In this way, while we are implementing our user interface, we can use setter and getter to display the correct element to user
- Later, we might turn this into an observer pattern to make our design cleaner.