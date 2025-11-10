package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class AnagramCheckerTest {
    private lateinit var checker: AnagramChecker

    @BeforeEach
    fun setUp() {
        checker = AnagramChecker()
    }

    @Test
    fun `isAnagram returns true for valid anagrams`() {
        assertTrue(checker.isAnagram("listen", "silent"))
        assertTrue(checker.isAnagram("evil", "vile"))
        assertTrue(checker.isAnagram("anagram", "nagaram"))
    }

    @Test
    fun `isAnagram returns false for non-anagrams`() {
        assertFalse(checker.isAnagram("hello", "world"))
        assertFalse(checker.isAnagram("test", "testing"))
        assertFalse(checker.isAnagram("abc", "def"))
    }

    @Test
    fun `isAnagram returns true for identical strings`() {
        assertTrue(checker.isAnagram("test", "test"))
        assertTrue(checker.isAnagram("", ""))
    }

    @Test
    fun `isAnagram returns false for different length strings`() {
        assertFalse(checker.isAnagram("short", "longer"))
        assertFalse(checker.isAnagram("a", "ab"))
    }

    @Test
    fun `checkAnagrams returns true for valid anagrams with normalization`() {
        assertTrue(checker.checkAnagrams("Listen", "Silent"))
        assertTrue(checker.checkAnagrams("The Eyes", "They See"))
        assertTrue(checker.checkAnagrams("A decimal point", "Im a dot in place"))
    }

    @Test
    fun `checkAnagrams returns false for non-anagrams`() {
        assertFalse(checker.checkAnagrams("Hello", "World"))
        assertFalse(checker.checkAnagrams("Test", "Best"))
    }

    @Test
    fun `checkAnagrams adds texts to history`() {
        checker.checkAnagrams("Listen", "Silent")
        
        val anagrams = checker.findAnagrams("Silent")
        assertTrue(anagrams.contains("listen"))
    }

    @Test
    fun `findAnagrams returns empty list when no anagrams exist`() {
        checker.checkAnagrams("Hello", "World")
        
        val anagrams = checker.findAnagrams("Test")
        assertTrue(anagrams.isEmpty())
    }

    @Test
    fun `findAnagrams returns anagrams from history`() {
        checker.checkAnagrams("Listen", "Silent")
        checker.checkAnagrams("Listen", "Hello")
        checker.checkAnagrams("Listen", "Enlist")
        
        val anagrams = checker.findAnagrams("Silent")
        assertEquals(2, anagrams.size)
        assertTrue(anagrams.contains("listen"))
        assertTrue(anagrams.contains("enlist"))
    }

    @Test
    fun `findAnagrams excludes the input itself`() {
        checker.checkAnagrams("Listen", "Silent")
        
        val anagrams = checker.findAnagrams("Listen")
        assertFalse(anagrams.contains("listen"))
        assertTrue(anagrams.contains("silent"))
    }

    @Test
    fun `findAnagrams returns distinct results`() {
        checker.checkAnagrams("Listen", "Silent")
        checker.checkAnagrams("Silent", "Enlist")
        checker.checkAnagrams("Listen", "Silent") // Duplicate
        
        val anagrams = checker.findAnagrams("Listen")
        val silentCount = anagrams.count { it == "silent" }
        assertEquals(1, silentCount)
    }

    @Test
    fun `test scenario from assignment - f2(A) returns B and D`() {
        // A, B, and D are anagrams
        // f1(A, B), f1(A, C), f1(A, D)
        checker.checkAnagrams("abc", "bca")  // A, B
        checker.checkAnagrams("abc", "xyz")  // A, C
        checker.checkAnagrams("abc", "cab")  // A, D
        
        // f2(A) should return [B, D]
        val anagrams = checker.findAnagrams("abc")
        assertEquals(2, anagrams.size)
        assertTrue(anagrams.contains("bca"))
        assertTrue(anagrams.contains("cab"))
        assertFalse(anagrams.contains("xyz"))
    }

    @Test
    fun `test scenario from assignment - f2(B) returns A and D`() {
        checker.checkAnagrams("abc", "bca")  // A, B
        checker.checkAnagrams("abc", "xyz")  // A, C
        checker.checkAnagrams("abc", "cab")  // A, D
        
        // f2(B) should return [A, D]
        val anagrams = checker.findAnagrams("bca")
        assertEquals(2, anagrams.size)
        assertTrue(anagrams.contains("abc"))
        assertTrue(anagrams.contains("cab"))
    }

    @Test
    fun `test scenario from assignment - f2(C) returns empty list`() {
        checker.checkAnagrams("abc", "bca")  // A, B
        checker.checkAnagrams("abc", "xyz")  // A, C
        checker.checkAnagrams("abc", "cab")  // A, D
        
        // f2(C) should return [] since C has no anagrams
        val anagrams = checker.findAnagrams("xyz")
        assertTrue(anagrams.isEmpty())
    }

    @Test
    fun `normalization handles mixed case and special characters`() {
        assertTrue(checker.checkAnagrams("A!B@C#", "c b a"))
        assertTrue(checker.checkAnagrams("Listen!!!", "Silent???"))
    }

    @Test
    fun `empty strings are handled correctly`() {
        assertTrue(checker.checkAnagrams("", ""))
        val anagrams = checker.findAnagrams("")
        assertTrue(anagrams.isEmpty())
    }
}
