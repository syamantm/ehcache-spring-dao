package com.syamantakm.dao;

import com.syamantakm.model.CacheEntry;

/**
 * @author Syamantak Mukhopadhyay
 */
public interface CacheEntryDAO {
    int createEntry(String name);

    void updateEntry(CacheEntry entry);

    void deleteEntry(int id);

    CacheEntry findById(int id);
}
