package com.osori.cave.domain.navigation.controller

import com.osori.cave.IntegrationTestSupporter
import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.navigation.infrastructure.UriPart.DepthType.FUNC
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import io.kotlintest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod.GET

internal class NavigationTreeControllerTest : IntegrationTestSupporter() {

    @Autowired
    private lateinit var controller: NavigationTreeController
    @Autowired
    private lateinit var repository: UriPartRepository

    @Test
    fun findAllNodes() {
        //Given
        repository.save(UriPart("root", "/", FUNC, GET))

        //When
        val resources = controller.findAllNodes()
        //Then
        resources.content.size shouldNotBe 0
    }

    @Test
    fun addChildren() {
    }

    @Test
    fun addNode() {
    }

    @Test
    fun findNode() {
    }

    @Test
    fun modifyChildren() {
    }

    @Test
    fun removeChildren() {
    }
}
