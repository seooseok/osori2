package com.osori.cave.user.privilege

import com.osori.cave.user.infrastructure.User


interface GrantHandle {

    fun addGrant(user: User, targetId: Long)

    fun addGrants(user: User, targetIds: List<Long>)

    fun removeGrant(user: User, targetId: Long)

    fun removeGrants(user: User, targetIds: List<Long>)

    fun resetGrant(user: User)
}


