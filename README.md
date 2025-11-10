# Anagram Checker

A simple CLI application that checks for anagrams and tracks input history.

## Features

### Feature #1: Check if two texts are anagrams

- Compares two input strings to determine if they are anagrams
- Follows the Wikipedia definition: ignores case and non-alphanumeric characters
- Stores both inputs in history for future lookups

### Feature #2: Find all anagrams from history

- Takes an input string and returns all previously entered texts that are anagrams of it
- Searches through all inputs from Feature #1 within the current program run
- Returns unique results excluding the input itself

## How to Run

```bash
./gradlew run
```

Or build and run the JAR:

```bash
./gradlew build
java -jar build/libs/beyonnex-test.jar
```

## Usage Example

```
Enter command (1/2/q): 1
Enter first text: Listen
Enter second text: Silent
Result: YES, they are anagrams

Enter command (1/2/q): 1
Enter first text: Listen
Enter second text: Hello
Result: NO, they are not anagrams

Enter command (1/2/q): 2
Enter text: Silent
Anagrams found: listen

Enter command (1/2/q): q
Goodbye!
```

## Implementation Notes

- Uses character frequency counting for anagram detection
- Normalizes input by converting to lowercase and filtering non-alphanumeric characters
- Maintains a simple in-memory history of all inputs
- No external dependencies required
