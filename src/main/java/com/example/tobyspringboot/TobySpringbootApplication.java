package com.example.tobyspringboot;

import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TobySpringbootApplication {

    public static void main(String[] args) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // WebServer interface - Tomcat, Jetty, ... abstract
        WebServer webServer = serverFactory.getWebServer((servletContext) -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    String name = req.getParameter("name");
                    // HttpStatus (enum)클래스에 value 메소드
                    resp.setStatus(HttpStatus.OK.value());
                    // string 으로 하드코딩 할 경우 오타의 위험이 있으므로, enum type 을 활용하자.
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    resp.getWriter().println("Hello " + name);
                }
            }).addMapping("/hello");
        });
        webServer.start();
    }

}
