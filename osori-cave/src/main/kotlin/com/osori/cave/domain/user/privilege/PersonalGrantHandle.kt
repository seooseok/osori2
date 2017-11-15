package com.osori.cave.domain.user.privilege

import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.domain.navigation.infrastructure.UriPartType.SERVICE
import com.osori.cave.domain.user.infrastructure.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PersonalGrantHandle
@Autowired constructor(private val uriPartRepository: UriPartRepository) : GrantHandle {
    override fun addGrants(user: User, uriPartIds: List<Long>) {
        val uriParts = this.findByUriParts(uriPartIds)
        uriParts.forEach(user::addBy)
    }

    override fun removeGrants(user: User, uriPartIds: List<Long>) {
        val uriParts = this.findByUriParts(uriPartIds)
        uriParts.forEach(user::remove)
    }

    override fun addGrant(user: User, uriPartId: Long) {
        val uriPart = findByUriPart(uriPartId)
        user.addBy(uriPart)
    }

    override fun removeGrant(user: User, uriPartId: Long) {
        val uriPart = findByUriPart(uriPartId)
        user.remove(uriPart)
    }

    override fun resetGrant(user: User) {
        user.getUriParts().forEach(user::remove)
    }


    private fun findByUriPart(uriPartId: Long): UriPart {
        return uriPartRepository.findOne(uriPartId) ?: throw IllegalArgumentException("not found uri part ($uriPartId)")
    }

    private fun findByUriParts(menuNodeIdGroup: List<Long>): List<UriPart> {
        return uriPartRepository.findByTypeAndIdInAndStatusTrue(SERVICE, menuNodeIdGroup)
    }

}
