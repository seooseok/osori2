package com.osori.cave.service

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.generator.UriPartGenerator
import com.osori.cave.navigation.MenuTreeService
import com.osori.cave.navigation.infrastructure.UriPartRepository
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class MenuTreeServiceIntegrationTest: IntegrationTestSupporter() {
    @Autowired
    private lateinit var repository: UriPartRepository

    @Autowired
    private lateinit var menuTreeService: MenuTreeService


    @Test
    fun `메뉴 트리 Full URI 생성 테스트`(){
        //Given
        val rootUriPart = UriPartGenerator().createTree()
        repository.save(rootUriPart)

        //When
        val nodes = menuTreeService.findNodes()

        //Then
        nodes.find { n -> n.fullUri == null } shouldBe null
    }

    @Test
    fun resetTree() {
        //Given
        val rootUriPart = UriPartGenerator().createTree()
        repository.save(rootUriPart)
        //When
        menuTreeService.resetTree()

        menuTreeService.findNodes().size shouldBe  1

    }

    @Test
    fun moveNode() {
        //Given
        val rootUriPart = UriPartGenerator().createTree()
        repository.save(rootUriPart)
        //When
        val part = rootUriPart.uriParts[0].uriParts[0]
        val targetParentPart = rootUriPart.uriParts[1]

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
        menuTreeService.removeNode(parts[partsSize -1].id!!)

        val nodeSize = menuTreeService.findNodes().size

        (partsSize - 1) shouldEqual nodeSize
    }

}

