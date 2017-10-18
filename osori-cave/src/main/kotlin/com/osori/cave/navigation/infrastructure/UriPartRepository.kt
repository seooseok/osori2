package com.osori.cave.navigation.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UriPartRepository : JpaRepository<UriPart, Long> {
    fun findByTypeAndStatusTrue(type: UriPartType): List<UriPart>
    fun findByTypeAndParentUriPartIsNull(type: UriPartType): UriPart?
    fun findByTypeAndIdInAndStatusTrue(type: UriPartType, idGroup: List<Long>): List<UriPart>
}
