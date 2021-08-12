package com.example.attendanceapimono.application.exception

class UserDeletedException : RuntimeException(message) {
    companion object {
        const val message = "user deleted"
    }
}

class UserNotFoundException : RuntimeException(message) {
    companion object {
        const val message = "user not found"
    }
}