package com.osori.cave.user.privilege


interface GrantService {

    fun addGrant(userId:Long, targetId:Long)

    fun removeGrant(userId:Long, targetId:Long)

    fun resetGrant(userId: Long)
}


