package com.example.attendanceapimono.adapter.infra.security

import com.example.attendanceapimono.domain.user.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

data class RoleAdapter(val role: UserRole): GrantedAuthority {
    override fun getAuthority() = this.role.name
}

class UserPrinciple(
    val id: UUID,
    var role: RoleAdapter = RoleAdapter(UserRole.MEMBER)
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(role)
    }

    override fun getPassword(): String = ""

    override fun getUsername(): String = id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}