package com.osori.cave.navigation.controller

import com.osori.cave.navigation.MenuTreeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/navigation-trees")
class TreeController
@Autowired constructor(private val menuTreeService: MenuTreeService){

    @GetMapping("")
    fun findAllNodes():List<MenuNodeResource>{
        return menuTreeService.findNodes()
    }

    @PostMapping("/node")
    fun createNode(menuNode: MenuNodeResource){
        menuTreeService.create(menuNode)
    }

    @PutMapping("/node/{id}")
    fun modifyNode(@PathVariable id:Long, menuNode: MenuNodeResource){
        menuNode.apply { this.id = id }
        menuTreeService.modifyNode(menuNode)
    }

    @DeleteMapping("/node/{id}")
    fun removeNode(@PathVariable id:Long){
        menuTreeService.removeNode(id)
    }

    @Deprecated("delete 구현이 힘들어서 이렇게 하지 말자.")
    @PostMapping("")
    fun saveTree(menuNodes:List<MenuNodeResource>){

        menuNodes.forEach { menuNode ->
            if(menuNode.id == null)
                menuTreeService.create(menuNode)
            else
                menuTreeService.modifyNode(menuNode)
        }
    }

}
