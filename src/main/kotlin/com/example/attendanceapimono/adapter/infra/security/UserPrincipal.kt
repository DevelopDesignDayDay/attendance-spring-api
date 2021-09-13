package com.example.attendanceapimono.adapter.infra.security

import com.example.attendanceapimono.domain.user.UserRole
import com.example.attendanceapimono.util.tryOrNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

data class RoleAdapter(val role: UserRole): GrantedAuthority {

    override fun getAuthority() = role.name

    companion object {
        fun valueOf(role: String) = tryOrNull {
            RoleAdapter(UserRole.valueOf(role))
        }
    }
}

class UserPrincipal(
    val id: UUID,
    private val roles: List<RoleAdapter>
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.toMutableList()
    }

    override fun getPassword(): String = ""

    override fun getUsername(): String = id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}