package com.osori.cave.model

import org.hibernate.annotations.Where
import javax.persistence.CascadeType.DETACH
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name = "PERMISSION")
class Permission(var name:String){

    var id:Long? = null
        private set
    var status = true

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "uriPart", fetch = LAZY, cascade = arrayOf(PERSIST, MERGE, REFRESH, DETACH))
    var uriParts:MutableList<UriPart> = arrayListOf()
        private set

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "permissionGrant", fetch = LAZY, cascade = arrayOf(PERSIST, MERGE, REFRESH, DETACH))
    var permissionGrants:MutableList<PermissionGrant> = arrayListOf()
        private set

    fun addBy(uriPart: UriPart){
        if(uriParts.contains(uriPart).not())
            uriParts.add(uriPart)

        uriPart
    }
}
