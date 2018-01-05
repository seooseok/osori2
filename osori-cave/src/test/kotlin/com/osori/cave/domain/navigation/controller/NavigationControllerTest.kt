package com.osori.cave.domain.navigation.controller

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.domain.navigation.infrastructure.UriPart.DepthType.FUNC
import com.osori.cave.domain.navigation.infrastructure.UriPart.DepthType.NAVI
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod.DELETE
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT

internal class NavigationControllerTest : IntegrationTestSupporter() {

    @Autowired
    private lateinit var controller: NavigationController

    @Test
    fun findAllNodes() {
        //When
        val nodes = controller.findAllNodes().content
        //Then
        nodes.size shouldNotBe 0
    }

    @Test
    fun addNodes() {
        //Given
        val resources = listOf(
                NodeResource("show", "test/{id}", FUNC, GET),
                NodeResource("add", "test/{id}", FUNC, POST),
                NodeResource("modify", "test/{id}", FUNC, PUT),
                NodeResource("delete", "test/{id}", FUNC, DELETE)
        )

        //When
        val results = controller.addNodes(resources).content
        //Then
        results.size shouldBe resources.size
        results.map { it.methodType } shouldBe listOf(GET, POST, PUT, DELETE)
        results.map { it.name } shouldBe listOf("show", "add", "modify", "delete")
        results.map { it.resource }.distinct() shouldBe listOf("test/{id}")
    }

    @Test
    fun findNode() {
        //When
        val node = controller.findAllNodes().content.last()
        //When
        val result = controller.findNode(node.id!!).content
        //Then
        result shouldBe node
    }

    @Test
    fun addNode() {
        //Given
        val node = NodeResource("add", "/add", FUNC, POST)
        //When
        val addedId = controller.addNode(node).content
        val addedNode = controller.findNode(addedId!!).content

        //Then
        addedNode.name shouldBe node.name
        addedNode.resource shouldBe node.resource
        addedNode.depthType shouldBe node.depthType
        addedNode.methodType shouldBe node.methodType
    }

    @Test
    fun modifyNode() {
        //Given
        val node = controller.findAllNodes().content.last()
        //When
        node.name = "changed name"
        node.depthType = NAVI
        node.resource = "/changed/resource"

        controller.modifyNode(node.id!!, node)

        val modifiedNode = controller.findNode(node.id!!).content
        //Then
        modifiedNode.name shouldBe "changed name"
        modifiedNode.depthType shouldBe NAVI
        modifiedNode.resource shouldBe "/changed/resource"
    }

    @Test
    fun removeNode() {
        //Given
        val node = controller.findAllNodes().content.last()
        //When
        controller.removeNode(node.id!!)
        val nodes = controller.findAllNodes().content
        //Then
        nodes.find { it.id == node.id } shouldBe null

    }
}
