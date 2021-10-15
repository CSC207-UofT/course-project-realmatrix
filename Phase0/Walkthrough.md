# Senario

- User can login
- User can see a list of packages
- User creates/delete a package
- User view the overall contents of the chosen package
- There are two options for a package, learn and review
- Click the 'learn' button, user can learn new card in the package and the proficiency of the card is updated to be higher
- Click the 'review' button, user can review the cards with proficiency greater than 0 and the proficiency of each cards will be updated based on the result of the review program



Mary is a second-year student and majors in Computer Science at the University of Toronto. She is studying CSC207 but always forgets the meaning of 'interface' and 'inheritance'. Hence, she wants to use this app to help her memorize these concepts. Since Mary is new to this app, she needs to sign up first. The app shows that her shelf is empty. She creates a new package and named it 'CSC207' on her shelf. She opens the 'CSC207' package which is currently empty. Then, Mary clicks the 'create a new card' button to create an empty card. Every card has two sides, named 'Term' and 'Definition'. Mary types 'inheritance' in the 'Term' side and types its definition 'the child class inherits all methods and variables from the parent class' in the ‘Definition’ side. Then, Mary creates another card for the term 'interface' and its definition. Thus, now Mary has two cards in the 'CSC207' package and both of the cards have proficiency of 0. 

There are two buttons in the package, 'Learn' and 'Review'. Mary clicked the 'Learn' button in package 'CSC207'. Then, the app generated a 'learning' program with cards of 0 proficiency in this package, to help Mary memorize them. After Mary finishes the 'learning program', the two cards' proficiency becomes higher. To further enrich the 'CSC207' package, Mary added more cards in this package with terms such as 'references' and 'primitives'. Every time she clicked the 'Learn' button in the ‘CSC207’ package, the app generated a 'learning program' for all new words (with proficiency 0) in the package. Several days later, Mary clicked the 'Review' button in package 'CSC207', then the app generated a test for a certain number of cards with proficiency higher than 0. The 'review program' will test on the card with a certain frequency based on the card's proficiency. Once the card has a proficiency high enough, it will no longer appear in the review program. The test result shows that Mary has a good memory about 'interface' but failed to memorize 'inheritance', so the proficiency of 'interface' was updated to be higher and the proficiency of 'inheritance' was updated to be lower. Currently, Mary only has one package for 'CSC207', but she could later create new packages to include more courses in her study. She could also delete or edit the existing packages or cards.
