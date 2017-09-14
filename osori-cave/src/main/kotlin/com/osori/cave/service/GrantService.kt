package com.osori.cave.service

import com.osori.cave.model.User
import com.osori.cave.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GrantService {
    @Autowired
    lateinit var userRepository:UserRepository

    fun userGrant(){
        val user = User("ad","ad")



    }

}
