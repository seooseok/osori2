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
        //Given
        val rootNode = MenuNode("root","/cave",UriPart.DepthType.MENU,RequestMethod.GET)
        val rootNodeId = UriPartManager(repository,rootNode).save()

        val menuNode1 = MenuNode("menu1","/menu1/{menu1}",UriPart.DepthType.MENU,RequestMethod.GET).apply {
            parentId = rootNodeId
        }
        val menuNode1Id = UriPartManager(repository,menuNode1).save()

        val menuNode2 = MenuNode("menu2","/menu2/{menu2}",UriPart.DepthType.MENU,RequestMethod.GET).apply {
            parentId = rootNodeId
        }
        val menuNode2Id = UriPartManager(repository,menuNode2).save()

        val funcNode = MenuNode("func","/func/{func}",UriPart.DepthType.FUNC,RequestMethod.GET).apply {
            parentId = menuNode1Id
        }
        val funcNodeId = UriPartManager(repository,funcNode).save()

        val menu1OfUriPart = repository.findOne(menuNode1Id)
        menu1OfUriPart.uriParts.find { u -> u.id ==  funcNodeId}?: throw IllegalStateException("error")

        //When
        UriPartManager(repository,funcNodeId).moveTo(menuNode2Id)



    }

}
