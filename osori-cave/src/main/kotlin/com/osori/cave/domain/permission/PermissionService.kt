package com.osori.cave.domain.permission

import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.domain.navigation.infrastructure.UriPartType.SERVICE
import com.osori.cave.domain.permission.controller.PermissionResource
import com.osori.cave.domain.permission.infrastructure.Permission
import com.osori.cave.domain.permission.infrastructure.PermissionRepository
import com.osori.cave.utils.toResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PermissionService
@Autowired constructor(val permissionRepository: PermissionRepository,
                       val uriPartRepository: UriPartRepository) {

    fun create(name: String, menuNodeIdGroup: List<Long>) {
        val uriParts = this.findByUriParts(menuNodeIdGroup)
        val permission = Permission(name)
        uriParts.forEach { permission.addBy(it) }

        save(permission)
    }

    fun modify(permissionId: Long, name: String) {
        val permission = findByPermission(permissionId)
        permission.name = name
    }

    fun addMenuNodes(permissionId: Long, menuNodeIdGroup: List<Long>) {
        val permission = findByPermission(permissionId)
        val uriParts = findByUriParts(menuNodeIdGroup)
        uriParts.forEach { permission.addBy(it) }
        save(permission)
    }

    fun removeMenuNodes(permissionId: Long, menuNodeIdGroup: List<Long>) {
        val permission = findByPermission(permissionId)
        val uriParts = findByUriParts(menuNodeIdGroup)
        permission.permissionUriPartMappings
                .filter { p -> uriParts.contains(p.uriPart) }
                .map { it.status = false }
    }

    fun remove(permissionId: Long) {
        val permission = findByPermission(permissionId)
        permission.status = false
        permission.userPermissionGrants.forEach { it.status = false }
    }

    fun recover(permissionId: Long) {
        val permission = findByPermission(permissionId)
        permission.status = true
    }

    fun findAll(): List<PermissionResource> {
        val permissions = permissionRepository.findByStatusIsTrue()
        return permissions.map(Permission::toResource)
    }

    fun findOne(permissionId: Long): PermissionResource {
        return findByPermission(permissionId).toResource()
    }

    private fun save(permission: Permission) = permissionRepository.save(permission)

    private fun findByPermission(permissionId: Long): Permission {
        return permissionRepository.findOne(permissionId) ?: throw IllegalStateException("not found permission($permissionId)")
    }

    private fun findByUriParts(menuNodeIdGroup: List<Long>): List<UriPart> {
        return uriPartRepository.findByTypeAndIdInAndStatusTrue(SERVICE, menuNodeIdGroup)
    }

}