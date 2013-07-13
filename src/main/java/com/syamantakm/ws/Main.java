package com.syamantakm.ws;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

import java.io.IOException;

/**
 * @author Syamantak Mukhopadhyay
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // Create and fire up an HTTP server
        ResourceConfig rc = new PackagesResourceConfig("com.syamantakm.ws.resources");
        HttpServer server = GrizzlyServerFactory.createHttpServer("http://localhost:4080", rc);
        System.in.read();
        server.stop();
    }
}
