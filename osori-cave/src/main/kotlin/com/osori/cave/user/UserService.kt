package com.osori.cave.user

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.osori.cave.user.controller.UserResource
import com.osori.cave.user.infrastructure.User
import com.osori.cave.user.infrastructure.UserRepository
import com.osori.cave.utils.Crypto
import com.osori.cave.utils.toResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException


@Transactional
@Service
class UserService
@Autowired constructor(private val repository: UserRepository){

    @Value(value = "\${cave.crypto.key}")
    private lateinit var cryptoKey:String


    fun create(loginId:String, name:String?,information: PersonalInformation? = null){
        val user = User(loginId,name)
        if(information != null && information.isEmpty().not()){
            user.information = Crypto(cryptoKey).enc(information.toJson())
        }
        save(user)
    }

    fun findOne(loginId: String): UserResource{
        val user = this.findByLoginId(loginId)

        val information = this.getPersonalInformation(user)

        return user.toResource(information)
    }

    private fun getPersonalInformation(user: User): PersonalInformation? {
        return user.information?.let {
            val json = Crypto(cryptoKey).dec(it)
            val mapper = jacksonObjectMapper()
            mapper.readValue(json, PersonalInformation::class.java)
        }
    }

    private fun findByLoginId(loginId: String): User {
        return repository.findByLoginId(loginId)?: throw IllegalArgumentException("not found user")
    }

    private fun save(user: User) = repository.save(user)

}
