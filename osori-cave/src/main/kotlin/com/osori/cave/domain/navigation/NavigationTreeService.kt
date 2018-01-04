package com.osori.cave.domain.navigation

import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.domain.navigation.infrastructure.UriPartType
import com.osori.cave.domain.user.infrastructure.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMethod

@Transactional
@Service
class NavigationTreeService
@Autowired constructor(private val repository: UriPartRepository) {

    val uriPartType = UriPartType.SERVICE

    fun create(name: String, resource: String, depthType: UriPart.DepthType, methodType: RequestMethod, parentId: Long? = null): Long {
        val uriPart = UriPart(name, resource, depthType, methodType)

        parentId?.let {
            val parentUriPart = repository.findOne(parentId)
            uriPart.setByParent(parentUriPart)
        }

        return save(uriPart)
    }

    fun findNode(nodeId: Long): UriPart {
        return repository.findOne(nodeId)
    }

    fun findNodes(ids: List<Long>): List<UriPart> {
        return repository.findAll(ids)
    }

    fun findNodes(): List<UriPart> {
        return findAll()
    }

    fun findNodes(user: User): List<UriPart> {
        return user.getUriParts()
    }

    fun modifyNode(id: Long, name: String, resource: String, depthType: UriPart.DepthType, methodType: RequestMethod) {
        val uriPart = repository.findOne(id).apply {
            this.name = name
            this.resource = resource
            this.depthType = depthType
            this.methodType = methodType
        }
    }

    fun removeNode(nodeId: Long) {
        val countOfLive = repository.countByTypeAndStatusTrue(uriPartType)

        if (countOfLive < 2)
            throw IllegalStateException("At least one root node must exist.")

        val uriPart = repository.findOne(nodeId)
        this.orphanRemove(uriPart)
    }

    private fun save(uriPart: UriPart): Long = repository.save(uriPart).id!!

    private fun orphanRemove(uriPart: UriPart) {
        uriPart.status = false
        if (uriPart.uriParts.isEmpty().not()) {
            uriPart.uriParts.map { u -> orphanRemove(u) }
        }
    }

    private fun findAll(): List<UriPart> = repository.findByTypeAndStatusTrue(uriPartType)

    fun moveNode(nodeId: Long, parentNodeId: Long) {
        val uriPart = repository.findOne(nodeId)
        val parentUriPart = repository.findOne(parentNodeId)
        uriPart.setByParent(parentUriPart)
    }
}
