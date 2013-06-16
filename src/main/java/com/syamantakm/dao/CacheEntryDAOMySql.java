package com.syamantakm.dao;

import com.syamantakm.cache.LocalCache;
import com.syamantakm.common.DAOException;
import com.syamantakm.model.CacheEntry;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Syamantak Mukhopadhyay
 */
@Transactional(readOnly = false)
public class CacheEntryDAOMySql implements CacheEntryDAO {
    private static final Logger LOGGER = Logger.getLogger(CacheEntryDAOMySql.class);

    public static final String TABLE_NAME = "cache_entry";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_TIMESTAMP = "TIMESTAMP";

    public static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " (NAME, TIMESTAMP)" +
            " VALUES (?, NOW())";

    public static final String SQL_SELECT_BY_ID = "SELECT ID, NAME, TIMESTAMP FROM " + TABLE_NAME + " WHERE ID = ?";
    public static final String SQL_SELECT_BY_NAME = "SELECT ID, NAME, TIMESTAMP FROM " + TABLE_NAME + " WHERE NAME = ?";

    public static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET NAME = ?, TIMESTAMP=NOW()" +
            " WHERE ID = ?";

    public static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";


    private LocalCache localCache;
    private JdbcTemplate jdbcTemplate;

    public CacheEntryDAOMySql(DataSource dataSource, LocalCache localCache) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.localCache = localCache;
    }

    @Override
    public int createEntry(final String name) {
        try {
            LOGGER.info(String.format("Creating cache entry with name : {%s}", name));
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    new PreparedStatementCreator() {
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps =
                                    connection.prepareStatement(SQL_INSERT, new String[] {"id"});
                            ps.setString(1, name);
                            return ps;
                        }
                    },
                    keyHolder);
            int id = keyHolder.getKey().intValue();

            // put it in the cache, so that next time it picks up from the cache
            if(id > 0) {
                CacheEntry entry = findById(id);
                localCache.put(id, entry);
            }

            return id;
        } catch (DataAccessException e) {
            LOGGER.error(String.format("Exception creating cache entry with name : %s", name), e);
            throw new DAOException(e);
        }
    }

    @Override
    public void updateEntry(CacheEntry entry) {
        try {
            LOGGER.info(String.format("Updating cache entry with id :%d, name : {%s}", entry.getId(), entry.getName()));
            int updatedRows = jdbcTemplate.update(SQL_UPDATE, entry.getName(), entry.getId());

            // put it in the cache, so that next time it picks up from the cache
            if(updatedRows > 0) {
                localCache.put(entry.getId(), entry);
            }
        } catch (DataAccessException e) {
            LOGGER.error(String.format("Exception updating cache entry with id : %d", entry.getId()), e);
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteEntry(int id) {
        try {
            LOGGER.info(String.format("Deleting cache entry with id : {%d}", id));
            int updatedRows = jdbcTemplate.update(SQL_DELETE, id);

            // remove from cache if delete if successful
            if(updatedRows > 0) {
                localCache.remove(id);
            }
        } catch (DataAccessException e) {
            LOGGER.error(String.format("Exception deleting cache entry with id : %d", id), e);
            throw new DAOException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CacheEntry findById(int id) {

        CacheEntry entry = null;
        entry = localCache.get(id, CacheEntry.class);
        if(entry != null) {
            return entry;
        }

        RowMapper<CacheEntry> mapper = new RowMapper<CacheEntry>() {
            @Override
            public CacheEntry mapRow(ResultSet resultSet, int i) throws SQLException {
                CacheEntry entry = new CacheEntry();
                entry.setId(resultSet.getInt(COLUMN_ID));
                entry.setName(resultSet.getString(COLUMN_NAME));
                entry.setTimestamp(resultSet.getTimestamp(COLUMN_TIMESTAMP).getTime());
                return entry;
            }
        };

        try {
            LOGGER.info(String.format("Retrieving cache entry with id : {%d}", id));
            Object[] param = {id};
            entry = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, param, mapper);

            // put it in the cache, so that next time it picks up from the cache
            localCache.put(entry.getId(), entry);

            return entry;
        } catch (DataAccessException e) {
            LOGGER.error(String.format("Exception retrieving cache entry with id : %d", id), e);
            throw new DAOException(e);
        }
    }
}
