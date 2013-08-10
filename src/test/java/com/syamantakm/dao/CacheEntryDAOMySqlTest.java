package com.syamantakm.dao;

import com.syamantakm.model.CacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * @author Syamantak Mukhopadhyay
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CacheEntryDAOMySqlTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CacheEntryDAO cacheEntryDAO;

    @Test
    public void shouldInsertToDataBase() {
        // Given
        String name = "test_" + System.currentTimeMillis();

        // When
        int id = cacheEntryDAO.createEntry(name);

        // Then
        CacheEntry entry = cacheEntryDAO.findById(id);
        assertNotNull(entry);
    }
}
