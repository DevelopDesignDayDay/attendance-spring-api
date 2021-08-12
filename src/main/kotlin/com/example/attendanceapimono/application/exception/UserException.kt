package com.example.attendanceapimono.application.exception

class UserDeletedException : RuntimeException(message) {
    companion object {
        const val message = "user deleted"
    }
}

class SocialProviderNotFoundException : RuntimeException(message) {
    companion object {
        const val message = "social provider not found"
    }
}

class UserNotFoundException : RuntimeException(message) {
    companion object {
        const val message = "user not found"
    }
}

class UserExistsException : RuntimeException(message) {
    companion object {
        const val message = "user exists"
    }
}