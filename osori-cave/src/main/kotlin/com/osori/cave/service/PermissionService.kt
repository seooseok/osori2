package com.osori.cave.service

import com.osori.cave.model.Permission
import com.osori.cave.model.UriPart
import com.osori.cave.repository.PermissionRepository
import com.osori.cave.repository.UriPartRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PermissionService
@Autowired constructor(private val permissionRepository: PermissionRepository,
                       private val uriPartRepository: UriPartRepository){

    fun create(name:String, menuNodeIdGroup:List<Long>){
        val uriParts = uriPartRepository.findByIdIn(menuNodeIdGroup)
        val permission = Permission(name)
        uriParts.forEach { permission.addBy(it) }
        save(permission)
    }

    fun modify(permissionId:Long, name: String){
        val permission = findByPermission(permissionId)
        permission.name = name
        save(permission)
    }

    fun addMenuNodes(permissionId:Long, menuNodeIdGroup:List<Long>){
        val permission = findByPermission(permissionId)
        val uriParts = findByUriParts(menuNodeIdGroup)
        uriParts.forEach { permission.addBy(it) }
        save(permission)
    }

    fun removeMenuNodes(permissionId:Long, menuNodeIdGroup:List<Long>){
        val permission = findByPermission(permissionId)
        val uriParts = findByUriParts(menuNodeIdGroup)
        permission.permissionUriPartMappings
                .filter { p -> uriParts.contains(p.uriPart) }
                .map { it.status = false }
        save(permission)
    }

    fun findAll(): List<Permission> {
        return permissionRepository.findByStatusIsTrue()
    }

    private fun save(permission: Permission){
        permissionRepository.save(permission)
    }


    private fun findByPermission(permissionId:Long): Permission {
        return permissionRepository.findOne(permissionId)?: throw IllegalStateException("not found permission")
    }

    private fun findByUriParts(menuNodeIdGroup:List<Long>): List<UriPart> {
        return uriPartRepository.findByIdIn(menuNodeIdGroup)
    }
}
