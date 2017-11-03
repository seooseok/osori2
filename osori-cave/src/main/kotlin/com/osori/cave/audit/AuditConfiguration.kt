package com.osori.cave.audit

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@Configuration
@EnableJpaAuditing
class AuditingConfiguration {

    @Bean
    internal fun auditorAware(): AuditorAwareImpl {
        return AuditorAwareImpl()
    }
}


class AuditorAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): String {
        /*val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request*/
        return "admin"
    }
}
