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
@Table(name = "USER")
class User (var loginId:String,
            var name:String){

    @Id
    @GeneratedValue
    var id:Long? = null
        private set
    internal var status = Status.WAIT

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = arrayOf(PERSIST, MERGE, REFRESH, DETACH))
    var userPermissionGrants:MutableList<UserPermissionGrant> = arrayListOf()
        private set

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = arrayOf(PERSIST, MERGE, REFRESH, DETACH))
    var userUriPartGrants:MutableList<UserUriPartGrant> = arrayListOf()
        private set

    internal enum class Status {
        ALLOW, REJECT, WAIT, EXPIRE
    }

    fun addBy(permission: Permission){
        val permissions = this.getPermissions()
        if(permissions.contains(permission).not()){
            UserPermissionGrant(this, permission)
        }
    }

    fun addBy(uriPart: UriPart){
        val uriParts = this.getUriParts()
        if(uriParts.contains(uriPart).not()){
            UserUriPartGrant(this,uriPart)
        }
    }

    fun getPermissions():List<Permission> {
        return userPermissionGrants.map { g -> g.permission }
    }

    fun getUriParts():List<UriPart> {
        return userUriPartGrants.map { u -> u.uriPart }
    }
}
