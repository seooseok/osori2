package com.osori.cave.domain.user.infrastructure

import com.osori.cave.domain.navigation.infrastructure.UriPart
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "URI_PART_GRANT")
class UserUriPartGrant(user: User, uriPart: UriPart) {
    @Id
    @GeneratedValue
    var id: Long? = null
        private set

    var status = true

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    var user: User = user
        private set

    @ManyToOne
    @JoinColumn(name = "uriPartId", nullable = false)
    var uriPart = uriPart
        private set
}
