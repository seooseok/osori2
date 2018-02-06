package com.osori.cave.domain.permission

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.domain.navigation.NavigationTreeService
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class DetailServiceIntegrationTest : IntegrationTestSupporter() {
    @Autowired
    private lateinit var permissionService: PermissionService
    @Autowired
    private lateinit var navigationTreeService: NavigationTreeService

    @Test
    fun create() {
        //Given
        val name = "sample permission"
        val nodes = navigationTreeService.findNodes()

        //When
        permissionService.create(name, nodes.map { n -> n.id!! })

        //Then
        val permissions = permissionService.findAll()

        permissions.size shouldNotBe 0
        permissions.find { it.name == name }!!.name shouldBe name
    }

    @Test
    fun modifyName() {
        //Given
        val name = "sample permission"
        val nodes = navigationTreeService.findNodes()

        permissionService.create(name, listOf(nodes[0].id!!))

        //When
        val permission = permissionService.findAll()[0]
        permissionService.modify(permission.id!!, "test name", listOf())

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
