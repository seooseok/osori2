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
        val rootUriPart = UriPart("root","/cave",UriPart.DepthType.MENU,RequestMethod.GET)
        val menu1UriPart = UriPart(  "menu1","/menu1/{menu1}",UriPart.DepthType.MENU,RequestMethod.GET)
        val menu2UriPart = UriPart(  "menu2","/menu2/{menu2}",UriPart.DepthType.MENU,RequestMethod.GET)
        val funcUriPart = UriPart(  "func","/func/{func}",UriPart.DepthType.FUNC,RequestMethod.GET)
        menu1UriPart.addBy(funcUriPart)
        rootUriPart.addBy(menu1UriPart)
        rootUriPart.addBy(menu2UriPart)

        repository.save(rootUriPart)

        //When
        val manager = UriPartManager(repository,funcUriPart.id!!)

        manager.changeParent(menu2UriPart.id!!).save()
        //Then
        repository.findOne(funcUriPart.id).parentUriPart!!.id shouldBe menu2UriPart.id
    }

}
