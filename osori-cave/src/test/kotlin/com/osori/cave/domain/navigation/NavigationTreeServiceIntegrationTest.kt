package com.osori.cave.domain.navigation

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.generator.UriPartGenerator
import com.osori.cave.utils.toResource
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod

internal class NavigationTreeServiceIntegrationTest : IntegrationTestSupporter() {
    @Autowired
    private lateinit var repository: UriPartRepository

    @Autowired
    private lateinit var navigationTreeService: NavigationTreeService

    @Test
    fun `메뉴 트리 Full URI 생성 테스트`() {
        //Given
        val rootUriPart = UriPartGenerator().createTree()
        repository.save(rootUriPart)

        //When
        val nodes = navigationTreeService.findNodes().map { it.toResource() }

        //Then
        nodes.find { n -> n.fullUri == null } shouldBe null
    }

    @Test
    fun createTree() {
        //When
        val id = navigationTreeService.create("new menu", "/menu", UriPart.DepthType.NAVI, RequestMethod.GET)
        //Then
        val savedNode = navigationTreeService.findNode(id)

        "new menu" shouldBe savedNode.name
        "/menu" shouldBe savedNode.resource
    }

    @Test
    fun moveNode() {
        //Given
        val rootUriPart = UriPartGenerator().createTree()
        repository.save(rootUriPart)
        //When
        val part = rootUriPart.uriParts.flatMap { it.uriParts }[0]
        val targetParentPart = rootUriPart.uriParts[0]

        navigationTreeService.moveNode(part.id!!, targetParentPart.id!!)
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
        navigationTreeService.removeNode(parts[partsSize - 1].id!!)

        val nodeSize = navigationTreeService.findNodes().size

        (partsSize - 1) shouldEqual nodeSize
    }
}
