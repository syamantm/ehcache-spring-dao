package com.syamantakm.ws.resources;

import com.syamantakm.model.CacheEntry;
import com.syamantakm.monitoring.annotation.Measurable;
import com.syamantakm.service.CacheEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Syamantak Mukhopadhyay
 */
@Path("/cache")
public class CacheResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheResource.class);

    private CacheEntryService service;

    public CacheResource(CacheEntryService service) {
        this.service = service;
    }

    @GET
    @Path("/entry/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Measurable(enabled = true)
    public CacheEntry getCacheEntry(@PathParam("id") int id) {
        LOGGER.info("CacheResource.getCacheEntry");
        return service.getEntry(id);
    }

    @PUT
    @Path("/new")
    @Produces(MediaType.TEXT_PLAIN)
    @Measurable(enabled = true)
    public String getCacheEntry(CacheEntry entry) {
        return String.valueOf(service.createEntry(entry.getName()));
    }


}
