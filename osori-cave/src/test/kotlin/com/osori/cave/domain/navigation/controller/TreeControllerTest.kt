package com.osori.cave.domain.navigation.controller

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.domain.navigation.MenuTreeService
import com.osori.cave.domain.navigation.infrastructure.UriPart.DepthType.FIELD
import com.osori.cave.domain.navigation.infrastructure.UriPart.DepthType.MENU
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.generator.UriPartGenerator
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod.GET

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TreeControllerTest : IntegrationTestSupporter() {
    @Autowired
    private lateinit var repository: UriPartRepository
    @Autowired
    private lateinit var menuTreeService: MenuTreeService
    @Autowired
    private lateinit var treeController: TreeController

    private lateinit var resources: MutableList<MenuNodeResource>

    @BeforeAll
    fun beforeAll() {
        val rootUriPart = UriPartGenerator().createTree()
        repository.save(rootUriPart)
        resources = menuTreeService.findNodes().toMutableList()
        resources.forEach { resource ->
            resource.apply {
                this.viewId = this.id
                this.viewParentId = this.parentId
            }
        }
    }

    @Test
    fun createNewLeafNode() {
        val copyResources = resources.toMutableList()
        //Given
        copyResources.add(MenuNodeResource("관리자 메뉴", "/admin", MENU, GET).apply {
            this.parentId = 1

            this.viewId = 50
            this.viewParentId = 1
        })

        //When
        treeController.saveTree(copyResources)
        //Then
        val uriPart = repository.findAll().find { it.name == "관리자 메뉴" }!!

        uriPart shouldNotBe null
        uriPart.viewId shouldBe 50L
        uriPart.viewParentId shouldBe 1L
        uriPart.parentUriPart shouldNotBe null
    }

    @Test
    fun moveLeafNode() {
        //Given
        val copyResources = resources.toMutableList()
        val parentNode = copyResources[copyResources.size - 1]

        val targetId = copyResources[copyResources.size / 2].apply {
            viewParentId = parentNode.viewId
            parentId = parentNode.id
            depthType = FIELD
        }.id

        //When
        treeController.saveTree(copyResources)
        //Then
        val uriPart = repository.findOne(targetId)!!

        uriPart.viewParentId shouldBe parentNode.viewId
        uriPart.parentUriPart!!.id shouldBe parentNode.id
        uriPart.depthType shouldBe FIELD
    }

}
