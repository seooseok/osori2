package com.osori.cave.model

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "URI_PART_GRANT")
class UriPartGrant(user: User, uriPart: UriPart) {

    var id:Long? = null
        private set

    var status = true

    @ManyToOne @JoinColumn(name = "userId", nullable = false, updatable = false)
    var user:User = user
        private set

    @ManyToOne @JoinColumn(name = "uriPartId", nullable = false, updatable = false)
    var uriPart:UriPart = uriPart
        private set
}
