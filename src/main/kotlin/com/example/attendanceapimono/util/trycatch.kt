package com.example.attendanceapimono.util

inline fun <T> tryOrNull(block: () -> T): T? {
    return try {
        block()
    } catch (e: Exception) {
        null
    }
}

inline fun <T> tryOrDefault(def: T, block: () -> T): T {
    return try {
        block()
    } catch (e: Exception) {
        def
    }
}