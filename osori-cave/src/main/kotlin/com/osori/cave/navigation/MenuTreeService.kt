package com.osori.cave.navigation

import com.osori.cave.navigation.controller.MenuNodeResource
import com.osori.cave.navigation.infrastructure.UriPart
import com.osori.cave.navigation.infrastructure.UriPartRepository
import com.osori.cave.navigation.infrastructure.UriPartType
import com.osori.cave.user.infrastructure.User
import com.osori.cave.utils.toResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MenuTreeService
@Autowired constructor(private val repository: UriPartRepository) {

    fun create(menuNodeResource: MenuNodeResource) {
        val uriPart = UriPart(menuNodeResource.name, menuNodeResource.resource, menuNodeResource.depthType, menuNodeResource.methodType).apply {
            sorting = menuNodeResource.sorting
        }

        if (menuNodeResource.parentId != null) {
            val parentUriPart = repository.findOne(menuNodeResource.parentId)
            uriPart.setByParent(parentUriPart)
        }
        save(uriPart)
    }

    fun findNode(nodeId:Long): MenuNodeResource {
        val uriPart = repository.findOne(nodeId)
        return uriPart.toResource()
    }

    fun findNodes():List<MenuNodeResource>{
        val uriParts = findAll()
        return uriParts.map { u -> u.toResource()}
    }

    fun findNodes(user: User):List<MenuNodeResource> {
        return user.getUriParts().map { u -> u.toResource() }
    }

    fun moveNode(nodeId: Long, parentNodeId:Long){
        val uriPart = repository.findOne(nodeId)
        val parentUriPart = repository.findOne(parentNodeId)
        uriPart.setByParent(parentUriPart)
    }

    fun modifyNode(menuNodeResource: MenuNodeResource){
        val uriPart = repository.findOne(menuNodeResource.id).apply {
            name = menuNodeResource.name
            resource = menuNodeResource.resource
            methodType = menuNodeResource.methodType
            depthType = menuNodeResource.depthType
            sorting = menuNodeResource.sorting
            viewId = menuNodeResource.viewId
            viewParentId = menuNodeResource.parentViewId
        }
    }

    fun removeNode(nodeId: Long){
        val uriPart = repository.findOne(nodeId)
        if(uriPart.parentUriPart == null)
            throw IllegalStateException("${nodeId} is root node. root node. can't remove")
        uriPart.status = false
    }

    fun resetTree(){
        val root = findRoot()
        root?.let { it.uriParts.map { u -> orphanRemove(u) } }
    }

    private fun save(uriPart: UriPart) = repository.save(uriPart)

    private fun orphanRemove(uriPart: UriPart){
        uriPart.status = false
        if(uriPart.uriParts.isEmpty().not()){
            uriPart.uriParts.map { u -> orphanRemove(u) }
        }
    }

    private fun findAll():List<UriPart>{
        return repository.findByTypeAndStatusTrue(UriPartType.SERVICE)
    }

    private fun findRoot():UriPart? {
        return repository.findByTypeAndParentUriPartIsNull(UriPartType.SERVICE)?: throw IllegalStateException("can't reset tree")
    }

}
