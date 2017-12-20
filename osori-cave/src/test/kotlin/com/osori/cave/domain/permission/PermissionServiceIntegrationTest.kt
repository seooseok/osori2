package com.osori.cave.domain.permission

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.domain.navigation.NavigationTreeService
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.generator.UriPartGenerator
import io.kotlintest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class PermissionServiceIntegrationTest : IntegrationTestSupporter() {
    @Autowired
    private lateinit var uriPartRepository: UriPartRepository
    @Autowired
    private lateinit var permissionService: PermissionService
    @Autowired
    private lateinit var navigationTreeService: NavigationTreeService

    @Test
    fun create() {
        //Given
        val name = "sample permission"
        uriPartRepository.save(UriPartGenerator().createTree())
        val nodes = navigationTreeService.findNodes()

        //When
        permissionService.create(name, nodes.map { n -> n.id!! })

        //Then
        val permissions = permissionService.findAll()

        permissions.size shouldBe 1
        permissions[0].name shouldBe name
    }

    @Test
    fun modify() {
        //Given
        val name = "sample permission"

        uriPartRepository.save(UriPartGenerator().createTree())
        val nodes = navigationTreeService.findNodes()

        permissionService.create(name, listOf(nodes[0].id!!))

        //When
        val permission = permissionService.findAll()[0]
        permissionService.modify(permission.id!!, "test name")

        //Than
        val modifiedPermission = permissionService.findAll()[0]
        modifiedPermission.name shouldBe "test name"
    }

    @Test
    fun addMenuNodes() {
    }

    @Test
    fun removeMenuNodes() {
    }
}
