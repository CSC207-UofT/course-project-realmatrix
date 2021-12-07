# Progress Report

## Major Decision and Worked Well
Our major design decision in Phase 2 is that we use `ProgramState` to store current user/pack/card, and `ProgramStateManager` (concrete class for `ProgramStateInputBoundary`)
to update the current entity. In this way, while we are implementing our user interface, we can use setter and getter to display the correct element to user.

Based on skleten codes in Phase 0, we optimized the database, tests and also our design and broadened original specification to extend easily our project with additional functionality, following feedbacks from TA. 
And we implemented all GUI and documentation.
In general, we obeyed Clean Architecture design principle and SOLID principle by having clear layers of entity, usecase and controller classes.

## Division of work summary
### Chloe Huang
- Implemented `PackListFrame` and `CardListFrame`; revised `gateway` to load items according to date and fixed writing bugs; implemented `ProgramState`, `ProgramStateManager` and `ProgramStateController`.
- https://github.com/CSC207-UofT/course-project-realmatrix/pull/30 and https://github.com/CSC207-UofT/course-project-realmatrix/pull/28. These pull requests implements fully-functional`PackFrame` (now called `PackListFrame`) and `CardFrame` (now called `CardListFrame`) with add/edit/search/sort/delete card/pack functionality, and wisely use different present methods `JList` and `JTable` for pack and card. 

### Xing Ling
- Worked on learn and review algorithm design, GUI design and implementation, code reviews, and testing in phase 2. 
- https://github.com/CSC207-UofT/course-project-realmatrix/pull/21 

   In this pull request, I implemented learn and review algorithm for our software and tested it worked through junit tests. I closed this one and opened another one. 
See https://github.com/CSC207-UofT/course-project-realmatrix/pull/15
due to teammate misusing github features :)

### Yifan Zhao
- Implemented `LearnFrame`.
- Fixed warnings.
- https://github.com/CSC207-UofT/course-project-realmatrix/pull/36

  This pull request finished all layouts of the Learn Frame and solved the issues like text wrapping and long definition that couldn't fits in the frame.

### Ziqi Shu
- Worked on tests for gateway and updated previous tests on use case classes and entity classes. 
- Added constants and exceptions. 
- Updated `ChangePasswordFrame` and `ChangeUsernameFrame`.
- https://github.com/CSC207-UofT/course-project-realmatrix/pull/35

  This pull request better implements user's framework and adds significant tests for loading data in and out.

### Jiarun Cai
- Implemented `CardFrame`, `EditCardFrame` and `AddCardFrame`.
- Revised `PackListFrame` and Implemented `EditPackFrame` and `AddPackFrame`.
- Worked on Progress Report.
- https://github.com/CSC207-UofT/course-project-realmatrix/pull/28

  This pull request implements `CardFrame`, including `AddCardFrame` and `EditCardFrame` and `AddPackFrame` and `EditPackFrame`.

### Runshi Yang
- Implemented `ReviewFrame`.
- Fixed up typos for all phase2 documents and write part of Design Document - Clean Architecture.
- https://github.com/CSC207-UofT/course-project-realmatrix/pull/34#issue-1066665240

  This pull request finished the basic implementation of the `ReviewFrame` and changed the layout of the frame.




