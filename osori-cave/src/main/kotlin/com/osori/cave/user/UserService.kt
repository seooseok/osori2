package com.osori.cave.user

import com.osori.cave.user.controller.UserResource
import com.osori.cave.user.infrastructure.User
import com.osori.cave.user.infrastructure.UserRepository
import com.osori.cave.utils.toResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException


@Transactional
@Service
class UserService
@Autowired constructor(private val repository: UserRepository){

    fun create(loginId:String, name:String?){
        User(loginId,name)
    }

    fun findOne(loginId: String): UserResource{
        val user = repository.findByLoginId(loginId)?: throw IllegalArgumentException("not found user")
        return user.toResource()
    }

    private fun findByLoginId(loginId: String): User {
        return repository.findByLoginId(loginId)?: throw IllegalArgumentException("not found user")
    }


}
