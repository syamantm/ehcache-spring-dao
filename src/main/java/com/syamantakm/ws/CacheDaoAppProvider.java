package com.syamantakm.ws;

import com.syamantakm.monitoring.spi.ApplicationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Syamantak Mukhopadhyay
 */
public class CacheDaoAppProvider implements ApplicationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheDaoAppProvider.class);

    @Override
    public String getApplicationName() {
        return "com.syamantak.spring.jersy";
    }

    @Override
    public Logger getApplicationLogger() {
        return LOGGER;
    }
}
