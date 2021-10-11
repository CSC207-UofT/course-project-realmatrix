# CRC Cards

## Entities

### User

- Responsibility:

  - Stores user ID

  - Stores packages user created

- Collaboration:

  - Pack

### Pack

- Responsibility:
  - Store Pack ID
  - Store Cards
- Collaboration:
  - Cards

### Card

- Responsibility:
  - Store Card ID
  - Store term and definition
- Collaboration

## Use_case

### UserManager

- Responsibility:
  - Manage Users
  - User's Login/Sign out
  - Keep Track of User
- Collaboration:
  - User

### PackManager

- Responsibility:
  - Manage Pack
  - Add/delete/edit/search
- Collaboration
  - Pack

### CardManager

- Responsibility:
  - Manage Card
  - Add/delete/edit/search
- Collaboration
  - Card

### TestGenerator

- Responsibility:
  - Raise Learning and Reviewing
- Collaboration
  - Card



