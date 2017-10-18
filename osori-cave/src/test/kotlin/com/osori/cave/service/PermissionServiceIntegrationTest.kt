package com.osori.cave.service

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.generator.UriPartGenerator
import com.osori.cave.navigation.MenuTreeService
import com.osori.cave.navigation.infrastructure.UriPartRepository
import com.osori.cave.permission.PermissionService
import io.kotlintest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class PermissionServiceIntegrationTest : IntegrationTestSupporter() {
    @Autowired
    private lateinit var uriPartRepository: UriPartRepository
    @Autowired
    private lateinit var permissionService: PermissionService
    @Autowired
    private lateinit var menuTreeService: MenuTreeService

    @Test
    fun create() {
        //Given
        val name = "sample permission"
        uriPartRepository.save(UriPartGenerator().createTree())
        val nodes = menuTreeService.findNodes()

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
        val nodes = menuTreeService.findNodes()

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
