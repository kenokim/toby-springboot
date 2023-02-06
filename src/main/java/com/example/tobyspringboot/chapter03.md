## Standalone servlet application
Containerless application 이란 서블릿 컨테이너에 대한 코드를 자동화하고, 개발자가 spring bean 의 개발에만 집중할 수 있도록 구성한 어플리케이션이다.

Servlet 이란 자바 표준 기술 interface 로, container 구현체로 Tomcat 을 들 수 있다.

웹 표준 프로토콜에 대해서는 완벽하게 알아야함

Request : method, path, http version, headers, message body
response : http version, status code, status text, headers, message body