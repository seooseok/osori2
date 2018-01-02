package com.osori.cave.domain.user

import au.com.console.jpaspecificationdsl.and
import au.com.console.jpaspecificationdsl.between
import au.com.console.jpaspecificationdsl.equal
import au.com.console.jpaspecificationdsl.isNull
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.osori.cave.domain.user.infrastructure.User
import com.osori.cave.domain.user.infrastructure.UserRepository
import com.osori.cave.utils.Crypto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.jpa.domain.Specifications
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import java.time.LocalDate

@Transactional
@Service
class UserService
@Autowired constructor(private val repository: UserRepository) {

    @Value(value = "\${cave.crypto.key}")
    private lateinit var cryptoKey: String

    fun create(loginId: String, name: String? = null, information: PersonalInformation? = null): Long {
        val user = User(loginId, name)
        if (information != null && information.isEmpty().not()) {
            user.information = Crypto(cryptoKey).enc(information.toJson())
        }
        return save(user)
    }

    fun createNotExistLoginId(loginId: String) {
        val user = repository.findByLoginId(loginId)
        if (user == null) {
            create(loginId)
        }
    }

    fun modify(id: Long, name: String?, information: PersonalInformation?, status: User.Status?): Long {
        val user = repository.findOne(id)
        name?.let { user.name = name }
        status?.let { user.status = it }

        if (information != null && information.isEmpty().not()) {
            user.information = Crypto(cryptoKey).enc(information.toJson())
        }

        return save(user)
    }

    fun findOne(loginId: String): Pair<User, PersonalInformation?> {
        val user = this.findByLoginId(loginId)

        val information = this.getPersonalInformation(user)

        return Pair(user, information)
    }

    fun findOne(id: Long): Pair<User, PersonalInformation?> {
        val user = repository.findOne(id)

        val information = this.getPersonalInformation(user)

        return Pair(user, information)
    }

    fun findUsers(userSearchCondition: UserSearchCondition): List<User> {
        return this.search(userSearchCondition)
    }

    fun expireUser(id: Long) {
        val user = repository.findOne(id)
        user.expire()
        save(user)
    }

    private fun search(userSearchCondition: UserSearchCondition): List<User> {
        return repository.findAll(userSearchCondition.toSpecifications())
    }

    private fun getPersonalInformation(user: User): PersonalInformation? {
        return user.information?.let {
            val json = Crypto(cryptoKey).dec(it)
            val mapper = jacksonObjectMapper()
            mapper.readValue(json, PersonalInformation::class.java)
        }
    }

    private fun findByLoginId(loginId: String): User {
        return repository.findByLoginId(loginId) ?: throw IllegalArgumentException("not found user by loginId ($loginId)")
    }

    private fun save(user: User): Long = repository.save(user).id ?: throw IllegalStateException("can't save user!")
}

data class UserSearchCondition(
        val startDate: LocalDate,
        val endDate: LocalDate,
        val loginId: String? = null,
        val name: String? = null,
        val status: User.Status? = null)

private fun UserSearchCondition.toSpecifications(): Specifications<User> {
    return and(name?.let { User::name.equal(it) },
            loginId?.let { User::loginId.equal(it) },
            status?.let { User::status.equal(it) },
            User::created.between(startDate, endDate),
            User::expired.isNull())
}
