package com.croacker.beantohtml.server;

import com.croacker.beantohtml.serivice.ResourceService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;

public class StaticHandler implements HttpHandler {

    private ResourceService resourceService;

    private String staticRoute;

    public StaticHandler(String staticRoute) {
        this.staticRoute = staticRoute;
    }

    private ResourceService getResourceService(){
        if(resourceService == null){
            resourceService = new ResourceService();
        }
        return resourceService;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        String path = uri.getPath().replace(staticRoute, "");
        InputStream inputStream = getResourceService().get(path);
        File file = new File(path).getCanonicalFile();

        if (inputStream == null) {
            String response = "404 (Not Found)\n";
            httpExchange.sendResponseHeaders(404, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            String mime = "text/html";
            if (path.substring(path.length() - 3).equals(".js")) mime = "application/javascript";
            if (path.substring(path.length() - 3).equals("css")) mime = "text/css";

            Headers h = httpExchange.getResponseHeaders();
            h.set("Content-Type", mime);
            httpExchange.sendResponseHeaders(200, 0);

            OutputStream outputStream = httpExchange.getResponseBody();
            final byte[] buffer = new byte[0x10000];
            int count;
            while ((count = inputStream.read(buffer)) >= 0) {
                outputStream.write(buffer, 0, count);
            }
            inputStream.close();
            outputStream.close();
        }
    }
}
