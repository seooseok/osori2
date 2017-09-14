package com.osori.cave.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "PERMISSION_URI_PART_MAPPING")
class PermissionUriPartMapping(permission: Permission, uripart:UriPart) {
    @Id
    @GeneratedValue
    var id:Long? = null
        private set
    var status = true

    @ManyToOne
    @JoinColumn(name = "permissionId", nullable = false)
    var permission = permission
    @ManyToOne
    @JoinColumn(name = "uriPartId", nullable = false)
    var uriPart = uripart
}
