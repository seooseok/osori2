package com.osori.cave.domain.permission.controller

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.utils.toResource
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class PermissionControllerTest : IntegrationTestSupporter() {
    @Autowired
    private lateinit var controller: PermissionController
    @Autowired
    private lateinit var uriPartRepository: UriPartRepository

    @Test
    fun findAllGroups() {
        //When
        val permissions = controller.findAllGroups().content
        //Then
        permissions.size shouldNotBe 0
    }

    @Test
    fun findGroup() {
        //Given
        val id = controller.findAllGroups().content.first().id
        //When
        val permission = controller.findGroup(id!!).content

        permission.id shouldBe id
        permission.menuNodes.size shouldBe 0
    }

    @Test
    fun createGroup() {
        //Given
        val name = "sample permission"
        val nodeResources = uriPartRepository.findAll().map { it.toResource() }
        //When
        val id = controller.createGroup(PermissionResource(name).apply { this.menuNodes = nodeResources }).content
        //Then
        id shouldNotBe null
    }
}
