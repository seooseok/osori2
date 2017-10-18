package com.osori.cave.user.privilege

import com.osori.cave.permission.infrastructure.Permission
import com.osori.cave.permission.infrastructure.PermissionRepository
import com.osori.cave.user.infrastructure.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PermissionGrantHandle
@Autowired constructor(private val permissionRepository: PermissionRepository) : GrantHandle {

    override fun addGrants(user: User, targetIds: List<Long>) {
        val permissions = permissionRepository.findByIdInAndStatusTrue(targetIds)
        permissions.forEach(user::addBy)
    }

    override fun removeGrants(user: User, targetIds: List<Long>) {
        val permissions = permissionRepository.findByIdInAndStatusTrue(targetIds)
        permissions.forEach(user::remove)
    }


    override fun addGrant(user: User, permissionId: Long) {
        val permission = findPermission(permissionId)
        user.addBy(permission)
    }

    override fun removeGrant(user: User, permissionId: Long) {
        val permission = findPermission(permissionId)
        user.remove(permission)
    }

    override fun resetGrant(user: User) {
        user.getPermissions().forEach(user::remove)
    }

    private fun findPermission(permissionId: Long): Permission {
        return permissionRepository.findOne(permissionId) ?: throw IllegalArgumentException("not found permission($permissionId)")
    }
}
