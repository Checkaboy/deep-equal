package com.checkaboy.deepequal.model.transaction;

/**
 * @author Taras Shaptala
 */
public class Snapshot {

    private long timestamp;
    private String checksum;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

}