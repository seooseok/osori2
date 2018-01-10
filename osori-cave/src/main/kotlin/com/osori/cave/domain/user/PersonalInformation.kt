package com.osori.cave.domain.user

import com.osori.cave.utils.Jackson

class PersonalInformation(var email: String? = null,
                          var phone: String? = null,
                          var position: String? = null,
                          var department: String? = null,
                          var comment: String? = null) {
    fun toJson(): String {
        val mapper = Jackson.getMapper()
        return mapper.writeValueAsString(this)
    }
}

fun PersonalInformation.isEmpty(): Boolean {
    return (email == null && phone == null && position == null && department == null && comment == null)
}
