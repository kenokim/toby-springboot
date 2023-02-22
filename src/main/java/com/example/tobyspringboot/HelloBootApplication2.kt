package com.example.tobyspringboot

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServer
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HelloBootApplication2 {
    fun main(args: Array<String>) {
        val serverFactory: ServletWebServerFactory = TomcatServletWebServerFactory()

        class CustomHttpServlet: HttpServlet() {
            override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
                when {
                    req.requestURI.equals("/hello") && req.method.equals(HttpMethod.GET.name) -> {
                        val name: String = req.getParameter("name")
                        resp.status = HttpStatus.OK.value()
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                        resp.writer.println("Hello $name") }
                    req.requestURI.equals("/user") -> {}
                    else -> resp.status = HttpStatus.NOT_FOUND.value()
                }

                if (req.requestURI.equals("/hello") && req.method.equals(HttpMethod.GET.name)) {
                    val name: String = req.getParameter("name")
                    resp.status = HttpStatus.OK.value()
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                    resp.writer.println("Hello $name")
                } else if (req.requestURI.equals("/user")) {
                    //
                } else {
                    resp.status = HttpStatus.NOT_FOUND.value()
                }
            }
        }

        val initializer = ServletContextInitializer {
            it.addServlet("frontController", CustomHttpServlet())
        }

        val webServer: WebServer = serverFactory.getWebServer(initializer)
    }
}