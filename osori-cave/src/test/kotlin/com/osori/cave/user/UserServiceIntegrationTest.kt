package com.osori.cave.user

import com.osori.cave.IntegrationTestSupporter
import io.kotlintest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

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
        val userResource = userService.findOne(loginId)
        userResource.loginId shouldBe loginId
    }

    @Test
    fun createWithPersonalInformationAndFindTest() {
        //Given
        val loginId = "5dolstory"
        val email = "elijah17@gmail.com"
        val information = PersonalInformation(email, "010-1234-1234")
        //When
        userService.create(loginId, "서오석", information)
        //Then
        val userResource = userService.findOne(loginId)
        userResource.loginId shouldBe loginId
        userResource.email shouldBe email
    }

    @Test
    fun modifyTest() {
        //Given
        val loginId = "5dolstory"
        val email = "elijah17@daum.net"
        val information = PersonalInformation("elijah17@gmail.com", "010-1234-1234")
        //When
        userService.create(loginId, "서오석", information)
        val userResource = userService.findOne(loginId)

        userService.modify(userResource.id!!, "서오석", PersonalInformation(email))

        //Then
        val modifiedUserResource = userService.findOne(loginId)

        modifiedUserResource.loginId shouldBe loginId
        modifiedUserResource.email shouldBe email
        modifiedUserResource.phone shouldBe null
    }

}
