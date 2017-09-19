package com.osori.cave.service

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.generator.UriPartGenerator
import com.osori.cave.repository.UriPartRepository
import io.kotlintest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class MenuTreeServiceIntegrationTest: IntegrationTestSupporter() {
    @Autowired
    private lateinit var repository: UriPartRepository

    @Autowired
    private lateinit var menuTreeService:MenuTreeService

    @Test
    fun `메뉴 트리 Full URI 생성 테스트`(){
        //Given
        val rootUriPart = UriPartGenerator().createTree(3)
        repository.save(rootUriPart)

        //When
        val nodes = menuTreeService.findNodes()

        //Then
        nodes.find { n -> n.fullUri == null } shouldBe null
    }

    @Test
    fun resetTree() {
        //Given
        val rootUriPart = UriPartGenerator().createTree(3)
        repository.save(rootUriPart)
        //When
        menuTreeService.resetTree()

        menuTreeService.findNodes().size shouldBe  1

    }

}
