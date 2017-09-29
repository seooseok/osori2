package com.osori.cave.nodetree.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UriPartRepository : JpaRepository<UriPart, Long> {
    fun findByStatusTrue():List<UriPart>
    fun findByParentUriPartIsNull(): UriPart?
    fun findByIdInAndStatusTrue(idGroup:List<Long>): List<UriPart>
}
