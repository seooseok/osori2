package com.osori.cave.generator

import com.osori.cave.navigation.infrastructure.UriPart
import com.osori.cave.navigation.infrastructure.UriPart.DepthType.FUNC
import com.osori.cave.navigation.infrastructure.UriPart.DepthType.MENU
import org.springframework.web.bind.annotation.RequestMethod.DELETE
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT


class UriPartGenerator {

    fun createTree(): UriPart {
        val root = UriPart("대쉬보드","/", MENU, GET)
        root.addBy(UriPart("사용자 전체 조회","/users",MENU,GET))

        root.addBy(UriPart("사용자 조회","/user/{id}",MENU,GET))
        root.addBy(UriPart("사용자 정보 수정", "/user/{id}",MENU,PUT))
        root.addBy(UriPart("사용자 만료", "/user/{id}",MENU,DELETE))

        val menuTree = UriPart("트리 조회", "/tree",MENU,GET)
        menuTree.addBy(UriPart("네비게이션 추가", "/node", FUNC,POST))
        menuTree.addBy(UriPart("네비게이션 수정","/node/{id}",FUNC,PUT))
        menuTree.addBy(UriPart("네비게이션 삭제","/node/{id}",FUNC,DELETE))
        root.addBy(menuTree)

        return root
    }
}
