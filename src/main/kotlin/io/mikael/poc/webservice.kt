package io.mikael.poc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.WebApplicationContext
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.config.annotation.WsConfigurerAdapter
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.Namespace
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.XPathParam
import org.springframework.ws.transport.http.MessageDispatcherServlet
import java.time.OffsetDateTime

private const val NS = "http://xmlns.oracle.com/apps/prc/poz/suppliers/exportService/outbound/"

@Endpoint
open class PrintXmlEndpoint
    @Autowired constructor(private val soapRepository: SoapRepository)
{

    private val log = org.slf4j.LoggerFactory.getLogger(javaClass)

    @PayloadRoot(namespace = NS, localPart = "printXML")
    @Namespace(prefix = "ns1", uri = NS)
    fun printXml(@XPathParam("/ns1:printXML/ns1:supplierData") supplierData: String) {
        soapRepository.save(SoapMessage(ts = OffsetDateTime.now(), message = supplierData))
        log.info("printXml $supplierData")
    }

}

@EnableWs
@Configuration
open class WebServiceConfig : WsConfigurerAdapter() {

    @Bean
    open fun messageDispatcherServlet(ctx: WebApplicationContext) =
            ServletRegistrationBean(MessageDispatcherServlet(ctx), "/ws/*")

}
