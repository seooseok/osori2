package com.osori.cave.model

import org.hibernate.annotations.Where
import org.springframework.web.bind.annotation.RequestMethod
import javax.persistence.CascadeType.DETACH
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "URI_PART")
class UriPart(var name:String,
              var resource:String,
              @Enumerated(STRING) var depthType:DepthType,
              @Enumerated(STRING) var methodType:RequestMethod) {

    @Id
    @GeneratedValue
    var id:Long? = null
        private set
    var status = true

    @ManyToOne
    @JoinColumn(name = "parentId")
    var parentUriPart: UriPart? = null

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "parentUriPart", fetch = LAZY, cascade = arrayOf(PERSIST,MERGE,REFRESH,DETACH))
    var uriParts: MutableList<UriPart> = arrayListOf()

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "uriPart", fetch = LAZY, cascade = arrayOf(PERSIST,MERGE,REFRESH,DETACH))
    var permissionUriPartMappings:MutableList<PermissionUriPartMapping> = arrayListOf()


    enum class DepthType {
        MENU,FUNC,FIELD
    }

    fun setByParent(parentUriPart: UriPart){
        this.parentUriPart = parentUriPart
        if(parentUriPart.uriParts.contains(this).not())
            parentUriPart.addBy(this)
    }

    fun addBy(uriPart: UriPart){
        this.uriParts.add(uriPart)
        if(this != uriPart.parentUriPart)
            uriPart.setByParent(this)
    }
}

