package com.osori.cave.user.privilege

import com.osori.cave.nodetree.infrastructure.UriPartRepository
import com.osori.cave.user.infrastructure.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PersonalGrantService
@Autowired constructor(private val userRepository: UserRepository,
                       private val uriPartRepository: UriPartRepository) : GrantService{

    override fun addGrant(userId: Long, uriPartId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeGrant(userId: Long, uriPartId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resetGrant(userId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
