package com.osori.cave.permission.infrastructure

import com.osori.cave.nodetree.infrastructure.UriPart
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "PERMISSION_URI_PART_MAPPING")
class PermissionUriPartMapping {
    constructor(permission: Permission, uriPart: UriPart){
        this.permission = permission
        if(permission.getUriParts().contains(uriPart).not())
            permission.permissionUriPartMappings.add(this)

        this.uriPart = uriPart
        if(uriPart.getPermission().contains(permission).not())
            uriPart.permissionUriPartMappings.add(this)
    }
    @Id
    @GeneratedValue
    var id:Long? = null
        private set
    var status = true

    @ManyToOne
    @JoinColumn(name = "permissionId", nullable = false)
    var permission: Permission
        private set

    @ManyToOne
    @JoinColumn(name = "uriPartId", nullable = false)
    var uriPart: UriPart
        private set
}
