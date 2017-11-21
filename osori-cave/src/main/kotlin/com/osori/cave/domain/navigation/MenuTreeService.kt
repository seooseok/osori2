package com.osori.cave.domain.navigation

import com.osori.cave.domain.navigation.controller.MenuNodeResource
import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.domain.navigation.infrastructure.UriPartType
import com.osori.cave.domain.user.infrastructure.User
import com.osori.cave.utils.toResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MenuTreeService
@Autowired constructor(private val repository: UriPartRepository) {

    fun create(menuNode: MenuNodeResource): Long {
        val uriPart = UriPart(menuNode.name, menuNode.resource, menuNode.depthType, menuNode.methodType).apply {
            sorting = menuNode.sorting
            viewId = menuNode.viewId
            viewParentId = menuNode.viewParentId
        }

        menuNode.parentId?.let {
            val parentUriPart = repository.findOne(menuNode.parentId)
            uriPart.setByParent(parentUriPart)
        }

        return save(uriPart)
    }

    fun findNode(nodeId: Long): MenuNodeResource {
        val uriPart = repository.findOne(nodeId)
        return uriPart.toResource()
    }

    fun findNodes(): List<MenuNodeResource> {
        val uriParts = findAll()
        return uriParts.map { u -> u.toResource() }
    }

    fun findNodes(user: User): List<MenuNodeResource> {
        return user.getUriParts().map { u -> u.toResource() }
    }

    fun modifyNode(menuNodeResource: MenuNodeResource) {
        val uriPart = repository.findOne(menuNodeResource.id).apply {
            name = menuNodeResource.name
            resource = menuNodeResource.resource
            methodType = menuNodeResource.methodType
            depthType = menuNodeResource.depthType
            sorting = menuNodeResource.sorting
            viewId = menuNodeResource.viewId
            viewParentId = menuNodeResource.viewParentId
        }

        menuNodeResource.parentId?.let {
            val parentUriPart = repository.findOne(it)
            uriPart.setByParent(parentUriPart)
        }
    }

    fun removeNode(nodeId: Long) {
        val uriPart = repository.findOne(nodeId)
        if (uriPart.parentUriPart == null)
            throw IllegalStateException("${nodeId} is root node. root node. can't remove")
        uriPart.status = false
    }

    fun resetTree() {
        val root = findRoot()
        root?.let { it.uriParts.map { u -> orphanRemove(u) } }
    }

    private fun save(uriPart: UriPart): Long = repository.save(uriPart).id!!

    private fun orphanRemove(uriPart: UriPart) {
        uriPart.status = false
        if (uriPart.uriParts.isEmpty().not()) {
            uriPart.uriParts.map { u -> orphanRemove(u) }
        }
    }

    private fun findAll(): List<UriPart> = repository.findByTypeAndStatusTrue(UriPartType.SERVICE)

    private fun findRoot(): UriPart? {
        return repository.findByTypeAndParentUriPartIsNull(UriPartType.SERVICE) ?: throw IllegalStateException("can't reset tree")
    }

    @Deprecated("쓸 일이 없네.")
    fun moveNode(nodeId: Long, parentNodeId: Long) {
        val uriPart = repository.findOne(nodeId)
        val parentUriPart = repository.findOne(parentNodeId)
        uriPart.setByParent(parentUriPart)
    }

}
