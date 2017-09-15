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
        this.uriPart = menuNode.toModel()
    }

    fun save(){
        repository.save(this.uriPart)
    }

    fun moveTo(parentNodeId:Long){
        val parentUriPart = repository.findOne(parentNodeId)
        uriPart.setBy(parentUriPart)
    }

    fun remove(){
        uriPart.status = false
        this.save()
    }


    private fun MenuNode.toModel(): UriPart {
        return UriPart(this.name,this.resource,this.depthType,this.methodType)
    }


}
