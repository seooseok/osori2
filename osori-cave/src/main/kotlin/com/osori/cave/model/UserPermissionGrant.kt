package com.osori.cave.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "USER_PERMISSION_GRANT")
class UserPermissionGrant(user:User, permission: Permission) {
    @Id
    @GeneratedValue
    var id:Long? = null
        private set
    var status = true

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    var user:User = user
        private set

    @ManyToOne
    @JoinColumn(name = "permissionId", nullable = false)
    var permission = permission
        private set
}


