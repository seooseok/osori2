package com.osori.cave.domain.user

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.domain.user.infrastructure.User
import io.kotlintest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

internal class UserServiceIntegrationTest : IntegrationTestSupporter() {

    @Autowired
    private lateinit var userService: UserService

    @Test
    fun createAndFindTest() {
        //Given
        val loginId = "5dolstory"
        //When
        userService.create(loginId = loginId, name = "서오석")
        //Then
        val user = userService.findOne(loginId).first
        user.loginId shouldBe loginId
    }

    @Test
    fun createWithPersonalInformationAndFindTest() {
        //Given
        val loginId = "5dolstory"
        val email = "elijah17@gmail.com"
        val information = PersonalInformation(email = email, phone = "010-1234-1234")
        //When
        userService.create(loginId, "서오석", information)
        //Then
        val (user, savedInformation) = userService.findOne(loginId)
        user.loginId shouldBe loginId
        savedInformation!!.email shouldBe email
    }

    @Test
    fun modifyTest() {
        //Given
        val loginId = "5dolstory"
        val email = "elijah17@daum.net"
        val information = PersonalInformation(email = "elijah17@gmail.com", phone = "010-1234-1234")
        //When
        userService.create(loginId, "서오석", information)
        val user = userService.findOne(loginId).first

        userService.modify(user.id!!, "서오석", PersonalInformation(email), User.Status.WAIT)

        //Then
        val (modifiedUser, modifiedIUInformation) = userService.findOne(loginId)

        modifiedUser.loginId shouldBe loginId
        modifiedIUInformation!!.email shouldBe email
        modifiedIUInformation!!.phone shouldBe null
    }

    @Test
    fun searchTest() {
        //Given
        val loginId = "5dolstory"
        val email = "elijah17@daum.net"
        val information = PersonalInformation(email = "elijah17@gmail.com", phone = "010-1234-1234")

        userService.create(loginId, "서오석", information)

        //When
        val users = userService.findUsers(UserSearchCondition(LocalDate.now(), LocalDate.now(), loginId))

        users[0].loginId shouldBe loginId

    }

}
