package com.syamantakm.service;

import com.syamantakm.dao.CacheEntryDAO;
import com.syamantakm.model.CacheEntry;
import com.syamantakm.monitoring.annotation.Latency;
import org.apache.log4j.Logger;

/**
 * @author Syamantak Mukhopadhyay
 */
public class CacheEntryService {
    private static final Logger LOGGER = Logger.getLogger(CacheEntryService.class);

    private CacheEntryDAO cacheEntryDAO;

    public CacheEntryService(CacheEntryDAO cacheEntryDAO) {
        this.cacheEntryDAO = cacheEntryDAO;
    }

    @Latency(enabled = true)
    public CacheEntry getEntry(int id) {
        LOGGER.info("CacheEntryService.getEntry");
        return this.cacheEntryDAO.findById(id);
    }

    //@Latency(enabled = true)
    public int createEntry(String name) {
        return this.cacheEntryDAO.createEntry(name);
    }
}
