package com.croacker.beantohtml.server;

import com.croacker.beantohtml.serivice.ResourceService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class StaticHandler implements HttpHandler {

    private ResourceService resourceService;

    private ResourceService getResourceService(){
        if(resourceService == null){
            resourceService = new ResourceService();
        }
        return resourceService;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        System.out.println("" + uri.getPath());
        String path = uri.getPath();
        File file = new File(path).getCanonicalFile();

        if (!file.isFile()) {
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

            OutputStream os = httpExchange.getResponseBody();
            FileInputStream fs = new FileInputStream(file);
            final byte[] buffer = new byte[0x10000];
            int count = 0;
            while ((count = fs.read(buffer)) >= 0) {
                os.write(buffer, 0, count);
            }
            fs.close();
            os.close();
        }
    }
}
