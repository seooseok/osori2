package com.osori.cave.model

import org.hibernate.annotations.Where
import javax.persistence.CascadeType.DETACH
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name = "PERMISSION")
class Permission(var name:String){

    @Id
    @GeneratedValue
    var id:Long? = null
        private set
    var status = true

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "permission", fetch = LAZY, cascade = arrayOf(PERSIST, MERGE, REFRESH, DETACH))
    var permissionUriPartMappings:MutableList<PermissionUriPartMapping> = arrayListOf()
        private set

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "permission", fetch = LAZY, cascade = arrayOf(PERSIST, MERGE, REFRESH, DETACH))
    var userPermissionGrants:MutableList<UserPermissionGrant> = arrayListOf()
        private set

}
