package io.mikael.poc

import java.time.OffsetDateTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity @Table(name = "soap_messages")
data class SoapMessage(

        @Id @GeneratedValue(strategy = IDENTITY)
        var id: Long? = null,

        var ip: String? = null,

        var ts: OffsetDateTime? = null,

        var message: String? = null

)

@RepositoryRestResource
interface SoapRepository : JpaRepository<SoapMessage, Long>
