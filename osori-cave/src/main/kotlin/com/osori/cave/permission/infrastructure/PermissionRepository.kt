package com.osori.cave.permission.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PermissionRepository : JpaRepository<Permission,Long>{
    fun findByStatusIsTrue(): List<Permission>
    fun findByIdInAndStatusIsTrue():List<Permission>
}
