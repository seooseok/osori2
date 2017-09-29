package com.osori.cave.user

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.user.infrastructure.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class UserServiceIntegrationTest: IntegrationTestSupporter() {
    @Autowired
    private lateinit var repository:UserRepository

    @Test
    fun create() {
       val id = repository.findAll()

    }

    @Test
    fun modify() {
    }

}
