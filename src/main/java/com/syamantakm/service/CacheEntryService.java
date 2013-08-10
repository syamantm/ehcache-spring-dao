package com.syamantakm.service;

import com.syamantakm.dao.CacheEntryDAO;
import com.syamantakm.model.CacheEntry;
import com.syamantakm.monitoring.annotation.Measurable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Syamantak Mukhopadhyay
 */
public class CacheEntryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheEntryService.class);

    private CacheEntryDAO cacheEntryDAO;

    public CacheEntryService(CacheEntryDAO cacheEntryDAO) {
        this.cacheEntryDAO = cacheEntryDAO;
    }

    @Measurable(enabled = true)
    public CacheEntry getEntry(int id) {
        LOGGER.info("CacheEntryService.getEntry");
        return this.cacheEntryDAO.findById(id);
    }

    @Measurable(enabled = true)
    public int createEntry(String name) {
        return this.cacheEntryDAO.createEntry(name);
    }
}
