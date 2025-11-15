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

## How to Test

```bash
./gradlew test
```

The project includes 13 comprehensive tests covering all features and edge cases.

## Implementation Details

### Performance Optimizations
- **O(1) average lookup time** using HashMap with canonical form keys (sorted characters)
- **Efficient storage** with Set-based deduplication
- **Minimal comparisons** by grouping anagrams together

### Design Decisions
- **Original text preservation**: Stores and returns original input text, not normalized versions
- **Canonical form**: Uses sorted character representation for fast anagram grouping
- **Private API**: Implementation details like `isAnagram` are hidden from public API
- **Both features track history**: Feature #2 also adds its input to history as specified

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
Anagrams found: Listen

Enter command (1/2/q): q
Goodbye!
```
