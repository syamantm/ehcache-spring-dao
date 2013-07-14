package com.syamantakm.ws.resources;

import com.syamantakm.SpringFactory;
import com.syamantakm.model.CacheEntry;
import com.syamantakm.service.CacheEntryService;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Syamantak Mukhopadhyay
 */
@Path("/cache")
public class CacheResource {
    private static final Logger LOGGER = Logger.getLogger(CacheResource.class);

    private CacheEntryService service = SpringFactory.INSTANCE.getBean(CacheEntryService.class);

    @GET
    @Path("/entry/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public CacheEntry getCacheEntry(@PathParam("id") int id) {
        LOGGER.info("CacheResource.getCacheEntry");
        return service.getEntry(id);
    }

    @PUT
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getCacheEntry(CacheEntry entry) {
        return String.valueOf(service.createEntry(entry.getName()));
    }


}
