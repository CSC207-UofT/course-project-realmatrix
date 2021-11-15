# Progress Report

## open questions
The `write` methods in `gateway` can write an entity's attributes into database. For example, `CardWriter`'s `write` method takes in a `Card` object, and performs `card.getTerm()` and `card.getDefinition()` to write the card's term and definition separately into database. **We are not sure if it's clean for `gateway` to call on entity methods.**
