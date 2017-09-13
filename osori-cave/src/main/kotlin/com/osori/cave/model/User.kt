package com.osori.cave.model

import org.hibernate.annotations.Where
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "USER")
class User (var loginId:String,
            var name:String){

    var id:Long? = null
        private set
    internal var status = Status.WAIT

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "permissionGrant", fetch = LAZY, cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH))
    var permissionGrants:MutableList<PermissionGrant> = arrayListOf()

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "uriPartGrant", fetch = LAZY, cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH))
    var uriPartGrants:MutableList<UriPartGrant> = arrayListOf()

    internal enum class Status {
        ALLOW, REJECT, WAIT, EXPIRE
    }
}
