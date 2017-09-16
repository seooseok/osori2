package com.osori.cave.repository

import com.osori.cave.model.UriPart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UriPartRepository : JpaRepository<UriPart, Long> {
    fun findByStatusTrue():List<UriPart>
    fun findByParentIdIsNull(): UriPart
}
