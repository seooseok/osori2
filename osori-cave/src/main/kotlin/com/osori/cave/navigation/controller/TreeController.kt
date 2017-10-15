package com.osori.cave.navigation.controller

import com.osori.cave.navigation.MenuTreeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/navigation")
class TreeController
@Autowired constructor(private val menuTreeService: MenuTreeService){

    @PostMapping("")
    fun saveTree(){

    }
}
