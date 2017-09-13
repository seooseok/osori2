package com.osori.cave.model

import org.hibernate.annotations.Where
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
import javax.persistence.Table

@Entity
@Table(name = "URI_PART")
class UriPart(var name:String,
              var resource:String,
              var depthType:DepthType,
              var methodType:RequestMethod) {

    var id:Long? = null
        private set
    var status = true

    @ManyToOne @JoinColumn(name = "parentId")
    var parentUriPart: UriPart? = null

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "parentUriPart", fetch = LAZY, cascade = arrayOf(PERSIST,MERGE,REFRESH,DETACH))
    var uriParts: MutableList<UriPart> = arrayListOf()


    enum class DepthType {
        MENU,FUNC,FIELD
    }
}

