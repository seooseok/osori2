package com.osori.cave.audit

import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(value = AuditingEntityListener::class)
abstract class Auditor {
    @CreatedBy
    @LastModifiedBy
    protected val auditor: String? = null
}

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener::class)
abstract class CUAudit : Auditor() {

    @CreatedDate
    var created: LocalDate? = null
        private set

    @LastModifiedDate
    protected var modified: LocalDate? = null
}

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener::class)
abstract class CUDAudit : CUAudit() {
    @Audited
    private var expired: LocalDate? = null     //만료일

    fun expire() {
        this.expired = LocalDate.now()
    }
}



