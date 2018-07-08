package com.croacker.beantohtml.app;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class AppBenToHtml {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8082), 0);

        SomeBean someBean = new SomeBean();
        someBean.setId("111");
        someBean.setName("Name");
        someBean.setCounter(0);

        HttpContext context = server.createContext("/", new EchoHandler(someBean));
        context.setAuthenticator(new Auth());

        context = server.createContext("/index", (exchange) ->{
            OutputStream os = exchange.getResponseBody();
            os.write("index".getBytes());
            os.close();
        });

        server.setExecutor(null);
        server.start();
    }

    static class EchoHandler implements HttpHandler {

        SomeBean someBean;

        public EchoHandler(SomeBean someBean) {
            this.someBean = someBean;
        }

        //        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder builder = new StringBuilder();

            builder.append("<h1>COUNTER: ").append(someBean.getCounter()).append("</h1>");
            builder.append("<h1>URI: ").append(exchange.getRequestURI()).append("</h1>");

            Headers headers = exchange.getRequestHeaders();
            for (String header : headers.keySet()) {
                builder.append("<p>").append(header).append("=")
                        .append(headers.getFirst(header)).append("</p>");
            }

            byte[] bytes = builder.toString().getBytes();
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
            someBean.incCounter();
        }
    }

    static class Auth extends Authenticator {
        @Override
        public Result authenticate(HttpExchange httpExchange) {
            if ("/forbidden".equals(httpExchange.getRequestURI().toString()))
                return new Failure(403);
            else
                return new Success(new HttpPrincipal("c0nst", "realm"));
        }
    }

}
