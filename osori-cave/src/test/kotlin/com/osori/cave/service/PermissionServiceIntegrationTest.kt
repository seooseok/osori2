package com.osori.cave.service

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.generator.UriPartGenerator
import com.osori.cave.repository.UriPartRepository
import io.kotlintest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class PermissionServiceIntegrationTest : IntegrationTestSupporter() {
    @Autowired
    private lateinit var uriPartRepository:UriPartRepository
    @Autowired
    private lateinit var permissionService:PermissionService
    @Autowired
    private lateinit var menuTreeService:MenuTreeService

    @Test
    fun create() {
        //Given
        val name = "sample permission"
        uriPartRepository.save(UriPartGenerator().createTree(2))
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

        uriPartRepository.save(UriPartGenerator().createTree(2))
        val nodes = menuTreeService.findNodes()

        //When
        permissionService.create(name, listOf(nodes[0].id!!))

    }

    @Test
    fun addMenuNodes() {
    }

    @Test
    fun removeMenuNodes() {
    }

}
