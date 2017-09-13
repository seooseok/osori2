package com.osori.cave.entity

import org.springframework.web.bind.annotation.RequestMethod
import javax.persistence.CascadeType.DETACH
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class UriDepth(var name:String,
               var resource:String,
               var depthType:DepthType,
               var methodType:RequestMethod) {

    var id:Long? = null
        private set

    @ManyToOne @JoinColumn(name = "parentId")
    var parentUriDepth:UriDepth? = null

    @OneToMany(mappedBy = "parentUriDepth", fetch = LAZY, cascade = arrayOf(PERSIST,MERGE,REFRESH,DETACH))
    var uriDepths: MutableList<UriDepth> = ArrayList<UriDepth>()

    var status:Boolean = true
}

enum class DepthType {
    MENU,FUNC,FIELD
}
