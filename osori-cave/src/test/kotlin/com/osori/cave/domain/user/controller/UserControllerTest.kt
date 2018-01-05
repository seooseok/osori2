package com.osori.cave.domain.user.controller

import com.osori.cave.IntegrationTestSupporter
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

internal class UserControllerTest : IntegrationTestSupporter() {

    @Autowired
    private lateinit var controller: UserController

    @Test
    fun search() {
        //Given
        val startDate = LocalDate.now().minusDays(7)
        val endDate = LocalDate.now().plusDays(2)
        //When
        val users = controller.search(startDate, endDate).content
        //Then
        users.size shouldNotBe 0
    }

    @Test
    fun findOneWithDetail() {
        //Given
        val userId = 1L
        //When
        val user = controller.findOneWithDetail(userId).content
        //Then
        user.id shouldBe userId
        user.name shouldNotBe null
    }
}
