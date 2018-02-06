package com.osori.cave.domain.permission

import com.nhaarman.mockito_kotlin.mock
import com.osori.cave.domain.navigation.infrastructure.UriPartRepository
import com.osori.cave.domain.permission.infrastructure.PermissionRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class PermissionServiceTest {

    private lateinit var permissionService: PermissionService
    private val permissionRepository = mock<PermissionRepository>()
    private val uriPartRepository = mock<UriPartRepository>()

    @BeforeAll
    private fun beforeAll() {
        this.permissionService = PermissionService(permissionRepository, uriPartRepository)
    }

    @Test
    fun modify() {
        //Given

    }
}
