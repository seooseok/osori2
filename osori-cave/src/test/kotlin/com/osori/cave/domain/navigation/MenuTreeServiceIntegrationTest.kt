package com.osori.cave.domain.navigation

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.domain.navigation.controller.MenuNodeResource
import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.generator.UriPartGenerator
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod

internal class MenuTreeServiceIntegrationTest : IntegrationTestSupporter() {
    @Autowired
    private lateinit var repository: UriPartRepository

    @Autowired
    private lateinit var menuTreeService: MenuTreeService


    @Test
    fun `메뉴 트리 Full URI 생성 테스트`() {
        //Given
        val rootUriPart = UriPartGenerator().createTree()
        repository.save(rootUriPart)

        //When
        val nodes = menuTreeService.findNodes()

        //Then
        nodes.find { n -> n.fullUri == null } shouldBe null
    }

    @Test
    fun createTree() {
        //Given
        val menuNode = MenuNodeResource("new menu", "/menu", UriPart.DepthType.NAVI, RequestMethod.GET)
        //When
        val id = menuTreeService.create(menuNode)
        //Then
        val savedNode = menuTreeService.findNode(id)

        menuNode.name shouldBe savedNode.name
        menuNode.resource shouldBe savedNode.resource
    }

    @Test
    fun moveNode() {
        //Given
        val rootUriPart = UriPartGenerator().createTree()
        repository.save(rootUriPart)
        //When
        val part = rootUriPart.uriParts.flatMap { it.uriParts }[0]
        val targetParentPart = rootUriPart.uriParts[0]

        menuTreeService.moveNode(part.id!!, targetParentPart.id!!)
        //Then
        val resultUriPart = repository.findOne(targetParentPart.id)
        resultUriPart.uriParts.contains(part) shouldBe true

    }

    @Test
    fun removeNode() {
        //Given
        val rootUriPart = UriPartGenerator().createTree()
        repository.save(rootUriPart)
        //When
        val parts = repository.findAll()
        val partsSize = parts.size
        //Then
        menuTreeService.removeNode(parts[partsSize - 1].id!!)

        val nodeSize = menuTreeService.findNodes().size

        (partsSize - 1) shouldEqual nodeSize
    }

}

