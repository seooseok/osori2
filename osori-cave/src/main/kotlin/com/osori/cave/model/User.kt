package com.osori.cave.model

import org.hibernate.annotations.Where
import javax.persistence.CascadeType
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
    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH))
    var userPermissionGrants:MutableList<UserPermissionGrant> = arrayListOf()

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH))
    var userUriPartGrants:MutableList<UserUriPartGrant> = arrayListOf()

    internal enum class Status {
        ALLOW, REJECT, WAIT, EXPIRE
    }
}
