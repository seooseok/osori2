package com.osori.cave.permission.infrastructure

import com.osori.cave.nodetree.infrastructure.UriPart
import com.osori.cave.user.infrastructure.User
import com.osori.cave.user.infrastructure.UserPermissionGrant
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

    fun addBy(user: User) {
        val users = this.getUsers()
        if(users.contains(user).not())
            UserPermissionGrant(user, this)
    }

    fun addBy(uriPart: UriPart){
        val uriParts = this.getUriParts()
        if(uriParts.contains(uriPart).not()){
            PermissionUriPartMapping(this, uriPart)
        }
    }

    fun removeBy(uriPart: UriPart){
        val mapping = permissionUriPartMappings.find { it.uriPart == uriPart }
        if(mapping != null)
            mapping.status = false
    }

    fun removeBy(user: User) {
        val mapping = userPermissionGrants.find { it.user == user }
        if(mapping != null)
            mapping.status = false
    }

    fun getUriParts(): List<UriPart> {
        return permissionUriPartMappings.map { p -> p.uriPart }
    }

    fun getUsers(): List<User> {
        return userPermissionGrants.map { g -> g.user }
    }



}
