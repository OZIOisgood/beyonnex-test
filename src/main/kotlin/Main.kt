package org.example

class AnagramChecker {
    private val inputHistory = mutableListOf<String>()

    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }

        val countS = mutableMapOf<Char, Int>()
        val countT = mutableMapOf<Char, Int>()

        for (i in s.indices) {
            countS[s[i]] = countS.getOrDefault(s[i], 0) + 1
            countT[t[i]] = countT.getOrDefault(t[i], 0) + 1
        }

        return countS == countT
    }

    fun checkAnagrams(text1: String, text2: String): Boolean {
        val normalized1 = normalizeText(text1)
        val normalized2 = normalizeText(text2)
        
        addToHistory(normalized1)
        addToHistory(normalized2)
        
        return isAnagram(normalized1, normalized2)
    }

    fun findAnagrams(input: String): List<String> {
        val normalized = normalizeText(input)
        return inputHistory
            .filter { it != normalized && isAnagram(normalized, it) }
            .distinct()
    }

    private fun normalizeText(text: String): String {
        return text.lowercase().filter { it.isLetterOrDigit() }
    }

    private fun addToHistory(text: String) {
        if (text.isNotEmpty()) {
            inputHistory.add(text)
        }
    }
}

fun main() {
    val checker = AnagramChecker()
    println("=== Anagram Checker ===")
    println("Commands:")
    println("  1 - Check if two texts are anagrams")
    println("  2 - Find all anagrams of a text")
    println("  q - Quit")
    println()

    while (true) {
        print("Enter command (1/2/q): ")
        when (readLine()?.trim()) {
            "1" -> {
                print("Enter first text: ")
                val text1 = readLine() ?: ""
                print("Enter second text: ")
                val text2 = readLine() ?: ""
                
                val result = checker.checkAnagrams(text1, text2)
                println("Result: ${if (result) "YES, they are anagrams" else "NO, they are not anagrams"}")
                println()
            }
            "2" -> {
                print("Enter text: ")
                val text = readLine() ?: ""
                
                val anagrams = checker.findAnagrams(text)
                if (anagrams.isEmpty()) {
                    println("No anagrams found in history")
                } else {
                    println("Anagrams found: ${anagrams.joinToString(", ")}")
                }
                println()
            }
            "q", "Q" -> {
                println("Goodbye!")
                break
            }
            else -> {
                println("Invalid command. Please enter 1, 2, or q")
                println()
            }
        }
    }
}