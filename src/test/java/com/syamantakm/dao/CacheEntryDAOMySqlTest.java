package com.syamantakm.dao;

import com.syamantakm.SpringFactory;
import com.syamantakm.model.CacheEntry;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * @author Syamantak Mukhopadhyay
 */
public class CacheEntryDAOMySqlTest {

    @Test
    public void shouldInsertToDataBase() {
        // Given
        CacheEntryDAO dao = SpringFactory.INSTANCE.getBean(CacheEntryDAO.class);
        String name = "test_" + System.currentTimeMillis();

        // When
        int id = dao.createEntry(name);

        // Then
        CacheEntry entry = dao.findById(id);
        assertNotNull(entry);
    }
}
