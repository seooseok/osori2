package com.osori.cave.domain.user.infrastructure

import com.osori.cave.audit.AuditCUD
import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.permission.infrastructure.Permission
import org.hibernate.annotations.Where
import java.time.format.DateTimeFormatter
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
class User(var loginId: String,
           var name: String?) : AuditCUD() {

    @Id
    @GeneratedValue
    var id: Long? = null
        private set

    var information: ByteArray? = null

    var status = Status.WAIT

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = arrayOf(PERSIST, MERGE, REFRESH, DETACH))
    var userPermissionGrants: MutableList<UserPermissionGrant> = arrayListOf()
        private set

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = arrayOf(PERSIST, MERGE, REFRESH, DETACH))
    var userUriPartGrants: MutableList<UserUriPartGrant> = arrayListOf()
        private set

    fun addBy(permission: Permission) {
        val permissions = this.getPermissions()
        if (permissions.contains(permission).not()) {
            UserPermissionGrant(this, permission)
        }
    }

    fun addBy(uriPart: UriPart) {
        val uriParts = this.getUriParts()
        if (uriParts.contains(uriPart).not()) {
            UserUriPartGrant(this, uriPart)
        }
    }

    fun remove(permission: Permission) {
        val permissionGrant = this.userPermissionGrants.find { it.permission.id == permission.id }
        if (permissionGrant != null)
            permissionGrant.status = false
    }

    fun remove(uriPart: UriPart) {
        val uriPartGrant = this.userUriPartGrants.find { it.uriPart.id == uriPart.id }
        if (uriPartGrant != null)
            uriPartGrant.status = false
    }

    fun getPermissions(): List<Permission> {
        return userPermissionGrants.map { g -> g.permission }
    }

    fun getUriParts(): List<UriPart> {
        return userUriPartGrants.map { u -> u.uriPart }
    }

    fun getCreated(): String? {
        return super.created?.let { it.format(DateTimeFormatter.ISO_DATE) }
    }

    enum class Status {
        ALLOW, REJECT, WAIT, EXPIRE
    }
}
