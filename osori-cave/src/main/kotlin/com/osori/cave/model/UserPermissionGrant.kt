package com.osori.cave.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "USER_PERMISSION_GRANT")
class UserPermissionGrant {
    constructor(user:User, permission: Permission){
        this.user = user
        if(user.getPermissions().contains(permission).not())
            user.userPermissionGrants.add(this)

        this.permission = permission
        if(permission.getUsers().contains(user).not())
            permission.userPermissionGrants.add(this)
    }
    @Id
    @GeneratedValue
    var id:Long? = null
        private set
    var status = true

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    var user:User
        private set

    @ManyToOne
    @JoinColumn(name = "permissionId", nullable = false)
    var permission:Permission
        private set


}


