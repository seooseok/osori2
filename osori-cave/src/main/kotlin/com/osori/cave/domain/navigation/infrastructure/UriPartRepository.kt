package com.osori.cave.domain.navigation.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UriPartRepository : JpaRepository<UriPart, Long> {
    @Query("SELECT COUNT(u) FROM UriPart u WHERE u.type = :type AND u.status = true")
    fun countByTypeAndStatusTrue(@Param("type") type: UriPartType): Long
    fun findByTypeAndStatusTrue(type: UriPartType): List<UriPart>
    fun findByTypeAndIdInAndStatusTrue(type: UriPartType, idGroup: List<Long>): List<UriPart>
}
