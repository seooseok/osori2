package com.osori.cave.generator

import com.osori.cave.navigation.infrastructure.UriPart
import org.springframework.web.bind.annotation.RequestMethod
import java.util.Random


class UriPartGenerator {
    private val random = Random(System.currentTimeMillis())

    fun createTree(childSize:Int): UriPart {
        val root = createUriPart()
        createChildDepth(root,childSize)

        root.uriParts.map { u -> createChildDepth(u,childSize) }
        return root
    }

    fun createChildDepth(uriPart: UriPart, size:Int){
        for(i in 1..size){
            uriPart.addBy(createUriPart())
        }
    }

    fun createUriPart(): UriPart {
        val name = listOf("추가","수정","삭제","조회").random()
        val resource = listOf("/menu/{menu}","/func/{func}","/category/{category}","/box/{box}").random()
        val methodType = RequestMethod.valueOf(listOf("GET","POST","PUT","DELETE").random())
        val depthType = UriPart.DepthType.valueOf(listOf("MENU","FUNC","FIELD").random())

        return UriPart(name, resource, depthType, methodType)
    }

    private fun List<String>.random(): String {
        if (this.isEmpty())
            return ""

        return this[(Math.random() * this.size).toInt()]
    }
}
