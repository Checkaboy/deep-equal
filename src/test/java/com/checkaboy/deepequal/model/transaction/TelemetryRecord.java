package com.checkaboy.deepequal.model.transaction;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Taras Shaptala
 */
public class TelemetryRecord {

    private boolean active;
    private Integer retries;
    private Double score;
    private String label;
    private ERecordStatus status;
    private String[] tags;
    private List<Integer> readings;
    private Set<String> markers;
    private Map<String, Long> attributes;
    private Snapshot snapshot;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ERecordStatus getStatus() {
        return status;
    }

    public void setStatus(ERecordStatus status) {
        this.status = status;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public List<Integer> getReadings() {
        return readings;
    }

    public void setReadings(List<Integer> readings) {
        this.readings = readings;
    }

    public Set<String> getMarkers() {
        return markers;
    }

    public void setMarkers(Set<String> markers) {
        this.markers = markers;
    }

    public Map<String, Long> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Long> attributes) {
        this.attributes = attributes;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

}
