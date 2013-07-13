package com.syamantakm.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Syamantak Mukhopadhyay
 */
@XmlRootElement
public class CacheEntry {
    private int id;
    private String name;
    private long timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CacheEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
