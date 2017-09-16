package com.osori.cave.service

import com.osori.cave.model.UriPart
import com.osori.cave.repository.UriPartRepository
import com.osori.cave.resource.MenuNode


class UriPartManager constructor(var repository: UriPartRepository) {

    private lateinit var uriPart:UriPart

    constructor(repository: UriPartRepository, id:Long): this(repository){
        this.uriPart = repository.findOne(id)
    }

    constructor(repository: UriPartRepository, menuNode:MenuNode): this(repository) {
        val uriPart = UriPart(menuNode.name,menuNode.resource,menuNode.depthType,menuNode.methodType)
        if (menuNode.parentId != null) {
            val parentUriPart = repository.findOne(menuNode.parentId)
            uriPart.setByParent(parentUriPart)
        }
        this.uriPart = uriPart
    }

    fun save(): Long{
        return repository.save(this.uriPart).id?: throw IllegalStateException("uri part can't save")
    }

    fun moveTo(parentNodeId:Long): UriPartManager{
        val parentUriPart = repository.findOne(parentNodeId)
        uriPart.setByParent(parentUriPart)
        return this
    }

    fun orphanRemove(): UriPartManager{
        if(uriPart.parentUriPart == null)
            throw IllegalStateException("root can't remove.")

        if(uriPart.uriParts.size > 0)
            uriPart.uriParts.map { child -> child.status = false }

        uriPart.status = false
        return this
    }


    private fun MenuNode.toModel(): UriPart {
        return UriPart(this.name,this.resource,this.depthType,this.methodType)
    }


}
