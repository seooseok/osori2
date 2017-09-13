package com.osori.cave.model

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "PERMISSION_GRANT")
class PermissionGrant(user:User, permission: Permission) {
    var id:Long? = null
        private set
    var status = true

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, updatable = false)
    var user:User = user
        private set

    @ManyToOne
    @JoinColumn(name = "permissionId", nullable = false, updatable = false)
    var permission = permission
        private set
}


