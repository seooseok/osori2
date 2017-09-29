package com.osori.cave.user.privilege

import com.osori.cave.permission.infrastructure.Permission
import com.osori.cave.permission.infrastructure.PermissionRepository
import com.osori.cave.user.infrastructure.User
import com.osori.cave.user.infrastructure.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PermissionGrantService
@Autowired constructor (private val userRepository: UserRepository,
                        private val permissionRepository:PermissionRepository) : GrantService {


    override fun addGrant(userId:Long, permissionId:Long){
        val user = findUser(userId)
        val permission = findPermission(permissionId)

        user.addBy(permission)
    }

    override fun removeGrant(userId: Long,permissionId: Long){
        val user = findUser(userId)
        val permission = findPermission(permissionId)
        user.remove(permission)
    }

    override fun resetGrant(userId: Long){
        val user = findUser(userId)
        user.getPermissions().forEach(user::remove)
    }

    private fun findPermission(permissionId: Long): Permission {
        return permissionRepository.findOne(permissionId)?: throw IllegalArgumentException("not found permission($permissionId)")
    }

    private fun findUser(userId: Long): User {
        return userRepository.findOne(userId)?: throw IllegalArgumentException("not found user($userId)")
    }

}
