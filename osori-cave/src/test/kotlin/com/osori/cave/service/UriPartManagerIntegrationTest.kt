package com.osori.cave.service

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.model.UriPart
import com.osori.cave.repository.UriPartRepository
import com.osori.cave.resource.MenuNode
import io.kotlintest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod


internal class UriPartManagerIntegrationTest : IntegrationTestSupporter() {
    @Autowired
    private lateinit var repository:UriPartRepository
    @Autowired
    private lateinit var menuTreeService:MenuTreeService
    @Test
    fun save() {
        //Given
        val menuNode = MenuNode("root","/",UriPart.DepthType.MENU,RequestMethod.GET)
        val manager = UriPartManager(repository,menuNode)
        //When
        val id = manager.save()
        //Then
        val node = menuTreeService.findNode(id)
        node.name shouldBe menuNode.name
        node.resource shouldBe menuNode.resource
        node.depthType shouldBe menuNode.depthType
        node.methodType shouldBe menuNode.methodType

    }

    @Test
    fun moveTo() {
    }

}
