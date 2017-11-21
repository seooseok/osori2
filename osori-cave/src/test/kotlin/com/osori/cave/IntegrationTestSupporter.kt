package com.osori.cave

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional


@Transactional
@ExtendWith(SpringExtension::class)
@ComponentScan
@SpringBootTest(classes = arrayOf(CaveApplication::class))
class IntegrationTestSupporter
