package com.example.attendanceapimono

import com.example.attendanceapimono.adapter.infra.security.AuthProperties
import com.example.attendanceapimono.adapter.infra.security.TokenProvider
import org.junit.jupiter.api.Test
import java.util.*

class TokenProviderTest {
    @Test
    fun test() {
        val auth = AuthProperties("ekrjbgu3io4bg3794g53894bgerui3b24t8u3ib4gui3wb4gei9284bhg9834bgueirngndf", 3600000);
        val provider = TokenProvider(auth);

        val uuid = UUID.randomUUID();
        println(uuid)

        val token = provider.createToken(uuid);
        println(token)

        val newUuid = provider.getUserId(token);
        println(newUuid)

        assert(uuid == newUuid);
    }
}