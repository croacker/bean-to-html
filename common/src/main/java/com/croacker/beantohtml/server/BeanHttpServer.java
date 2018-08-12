package com.croacker.beantohtml.server;

import com.croacker.beantohtml.serivice.HtmlService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeanHttpServer extends HttpServer {

    private HtmlService htmlService = new HtmlService();

    private HttpServer httpServer;

    private String staticHandlerRoute;

    private BeanHttpServer(InetSocketAddress inetSocketAddress, int backlog) throws IOException {
        HttpServerProvider provider = HttpServerProvider.provider();
        httpServer = provider.createHttpServer(inetSocketAddress, backlog);
    }

    public static BeanHttpServer create() throws IOException {
        return create(null, 0);
    }

    public static BeanHttpServer create(InetSocketAddress inetSocketAddress, int backlog) throws IOException {
        BeanHttpServer beanHttpServer = new BeanHttpServer(inetSocketAddress, backlog);
        return beanHttpServer;
    }

    @Override
    public void bind(InetSocketAddress inetSocketAddress, int i) throws IOException {
        httpServer.bind(inetSocketAddress, i);
    }

    @Override
    public void start() {
        String route = "/" + getStaticHandlerRoute();
        createContext(route, new StaticHandler(route));
        httpServer.start();
    }

    @Override
    public void setExecutor(Executor executor) {
        httpServer.setExecutor(executor);
    }

    @Override
    public Executor getExecutor() {
        return httpServer.getExecutor();
    }

    @Override
    public void stop(int i) {
        httpServer.stop(i);
    }

    @Override
    public HttpContext createContext(String s, HttpHandler httpHandler) {
        return httpServer.createContext(s, httpHandler);
    }

    public HttpContext createBeanContext(String s, BeanHandler beanHandler) {
        return httpServer.createContext(s, exchange -> {
                    String methodName = exchange.getRequestMethod();
                    if (methodName.equals("GET")) {
                        byte[] data = htmlService.toHtml(beanHandler.get(), getStaticHandlerRoute());
                        exchange.sendResponseHeaders(200, data.length);
                        OutputStream stream = exchange.getResponseBody();
                        stream.write(data);
                        stream.close();
                    } else if (methodName.equals("POST")) {
                        Headers headers = exchange.getRequestHeaders();
                        int contentLength = Integer.parseInt(headers.getFirst("Content-length"));
                        byte[] data = new byte[contentLength];

                        exchange.getRequestBody().read(data);

                        String dataStr = new String(data);
                        Map<String, String> parameters = Stream.of(dataStr.split("&"))
                                .map(el -> el.split("="))
                                .collect(Collectors.toMap(e -> e[0], e -> e[1]));
                        htmlService.toBean(beanHandler.get(), parameters);

                        data = htmlService.toHtml(beanHandler.get(), getStaticHandlerRoute());
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, data.length);
                        OutputStream stream = exchange.getResponseBody();
                        stream.write(data);
                        stream.close();
                    }
                }
        );
    }

    @Override
    public HttpContext createContext(String s) {
        return httpServer.createContext(s);
    }

    @Override
    public void removeContext(String s) throws IllegalArgumentException {
        httpServer.removeContext(s);
    }

    @Override
    public void removeContext(HttpContext httpContext) {
        httpServer.removeContext(httpContext);
    }

    @Override
    public InetSocketAddress getAddress() {
        return httpServer.getAddress();
    }

    private String getStaticHandlerRoute(){
        if (staticHandlerRoute == null){
            staticHandlerRoute = "static-" + hashCode();
        }
        return staticHandlerRoute;
    }
}
