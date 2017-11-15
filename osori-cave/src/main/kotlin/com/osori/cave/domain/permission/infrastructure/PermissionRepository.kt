package com.osori.cave.domain.permission.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PermissionRepository : JpaRepository<Permission, Long> {
    fun findByStatusIsTrue(): List<Permission>
    fun findByIdInAndStatusTrue(ids: List<Long>): List<Permission>
}
