package com.syamantakm.ws.resources;

import com.syamantakm.SpringFactory;
import com.syamantakm.dao.CacheEntryDAO;
import com.syamantakm.model.CacheEntry;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Syamantak Mukhopadhyay
 */
@Path("/cache")
public class CacheResource {

    private CacheEntryDAO cacheEntryDAO = SpringFactory.INSTANCE.getBean(CacheEntryDAO.class);

    @GET
    @Path("/entry/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public CacheEntry getCacheEntry(@PathParam("id") int id) {
        return cacheEntryDAO.findById(id);
    }

    @PUT
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getCacheEntry(CacheEntry entry) {

        return  String.valueOf(cacheEntryDAO.createEntry(entry.getName()));
    }


}
