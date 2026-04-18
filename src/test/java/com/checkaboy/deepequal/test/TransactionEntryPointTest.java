package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.comparator.array.ArrayComparator;
import com.checkaboy.deepequal.comparator.array.strategy.OrderedArrayComparisonStrategy;
import com.checkaboy.deepequal.comparator.collection.CollectionComparator;
import com.checkaboy.deepequal.comparator.collection.strategy.OrderedCollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.collection.strategy.UnorderedCollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.field.builder.FieldComparatorBuilder;
import com.checkaboy.deepequal.comparator.map.MapComparator;
import com.checkaboy.deepequal.comparator.map.strategy.UnorderedMapComparisonStrategy;
import com.checkaboy.deepequal.comparator.object.IObjectComparator;
import com.checkaboy.deepequal.comparator.object.builder.ObjectComparatorBuilder;
import com.checkaboy.deepequal.comparator.transaction.ComparisonTransaction;
import com.checkaboy.deepequal.context.cache.ComparisonContext;
import com.checkaboy.deepequal.diff.array.builder.ArrayDiffCollectorBuilder;
import com.checkaboy.deepequal.diff.collection.builder.CollectionDiffCollectorBuilder;
import com.checkaboy.deepequal.diff.container.DiffNode;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.field.builder.FieldDiffCollectorBuilder;
import com.checkaboy.deepequal.diff.map.builder.MapDiffCollectorBuilder;
import com.checkaboy.deepequal.diff.object.IObjectDiffCollector;
import com.checkaboy.deepequal.diff.object.builder.ObjectDiffCollectorBuilder;
import com.checkaboy.deepequal.diff.transaction.DiffCollectionTransaction;
import com.checkaboy.deepequal.model.transaction.ERecordStatus;
import com.checkaboy.deepequal.model.transaction.Snapshot;
import com.checkaboy.deepequal.model.transaction.TelemetryRecord;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Taras Shaptala
 */
public class TransactionEntryPointTest {

    @Test
    public void comparisonTransaction_shouldCompareAllSupportedDataTypes() {
        ComparisonTransaction<TelemetryRecord, TelemetryRecord> transaction = new ComparisonTransaction<>(
                createTelemetryComparator(),
                ComparisonContext::new
        );

        TelemetryRecord source = createBaselineRecord();
        TelemetryRecord target = createBaselineRecord();

        Assert.assertTrue(transaction.compare(source, target));

        target.getSnapshot().setChecksum("snapshot-changed");
        Assert.assertFalse(transaction.compare(source, target));
    }

    @Test
    public void comparisonTransaction_shouldHandleNullValues() {
        ComparisonTransaction<TelemetryRecord, TelemetryRecord> transaction = new ComparisonTransaction<>(
                createTelemetryComparator(),
                ComparisonContext::new
        );

        TelemetryRecord source = createBaselineRecord();
        TelemetryRecord target = createBaselineRecord();

        source.setTags(null);
        target.setTags(null);
        source.setReadings(null);
        target.setReadings(null);
        source.setMarkers(null);
        target.setMarkers(null);
        source.setAttributes(null);
        target.setAttributes(null);
        source.setSnapshot(null);
        target.setSnapshot(null);

        Assert.assertTrue(transaction.compare(source, target));
        Assert.assertFalse(transaction.compare(source, null));
        Assert.assertTrue(transaction.compare(null, null));
    }

    @Test
    public void comparisonTransaction_shouldCreateNewContextOnEachCall() {
        AtomicInteger contextCreateCount = new AtomicInteger();
        ComparisonTransaction<Integer, Integer> transaction = new ComparisonTransaction<>(
                (comparisonContext, source, target) -> Objects.equals(source, target),
                () -> {
                    contextCreateCount.incrementAndGet();
                    return new ComparisonContext();
                }
        );

        Assert.assertTrue(transaction.compare(10, 10));
        Assert.assertFalse(transaction.compare(10, 11));
        Assert.assertEquals(2, contextCreateCount.get());
    }

    @Test
    public void diffTransaction_shouldCollectStructuredDiffForAllDataTypes() {
        DiffCollectionTransaction<TelemetryRecord, TelemetryRecord> transaction = new DiffCollectionTransaction<>(
                createTelemetryDiffCollector(),
                ComparisonContext::new,
                DiffNode::new,
                "record"
        );

        TelemetryRecord source = createBaselineRecord();
        TelemetryRecord target = createBaselineRecord();
        target.setActive(false);
        target.setRetries(99);
        target.setScore(90.8);
        target.setStatus(ERecordStatus.ARCHIVED);
        target.getTags()[1] = "hotfix";
        target.getReadings().set(0, 500);
        target.getMarkers().remove("CANARY");
        target.getAttributes().put("version", 2L);
        target.getSnapshot().setChecksum("snapshot-changed");

        IDiffNode diffRoot = transaction.collect(source, target, "ignored.path");

        Assert.assertNotNull(diffRoot);
        String log = diffToLog(diffRoot);
        System.out.println(log);
        Assert.assertTrue(log.contains("record.active"));
        Assert.assertTrue(log.contains("record.retries"));
        Assert.assertTrue(log.contains("record.score"));
        Assert.assertTrue(log.contains("record.status"));
        Assert.assertTrue(log.contains("record.tags[1]"));
        Assert.assertTrue(log.contains("record.readings[0]"));
        Assert.assertTrue(log.contains("record.markers"));
        Assert.assertTrue(log.contains("record.attributes"));
        Assert.assertTrue(log.contains("record.snapshot.checksum"));
    }

    @Test
    public void diffTransaction_shouldSupportRootNameMutationThroughSetter() {
        DiffCollectionTransaction<TelemetryRecord, TelemetryRecord> transaction = new DiffCollectionTransaction<>(
                createTelemetryDiffCollector(),
                ComparisonContext::new,
                DiffNode::new
        );
        transaction.setRootName("customRoot");

        TelemetryRecord source = createBaselineRecord();
        TelemetryRecord target = createBaselineRecord();
        target.setLabel("changed");

        IDiffNode diffRoot = transaction.collect(source, target, "any.path");

        Assert.assertNotNull(diffRoot);
        Assert.assertEquals("customRoot", diffRoot.getPath());
        Assert.assertTrue(diffToLog(diffRoot).contains("customRoot.label"));
    }

    @Test
    public void diffTransaction_shouldReturnNullWhenObjectsAreEqual() {
        DiffCollectionTransaction<TelemetryRecord, TelemetryRecord> transaction = new DiffCollectionTransaction<>(
                createTelemetryDiffCollector(),
                ComparisonContext::new,
                DiffNode::new,
                "record"
        );

        TelemetryRecord source = createBaselineRecord();
        TelemetryRecord target = createBaselineRecord();

        Assert.assertNull(transaction.collect(source, target, "unused"));
    }

    private IObjectComparator<TelemetryRecord, TelemetryRecord> createTelemetryComparator() {
        return ObjectComparatorBuilder.of(TelemetryRecord.class, TelemetryRecord.class)
                .put("active", FieldComparatorBuilder.oneObjectFieldComparator(TelemetryRecord::isActive))
                .put("retries", FieldComparatorBuilder.oneObjectFieldComparator(TelemetryRecord::getRetries))
                .put("score", FieldComparatorBuilder.oneObjectFieldComparator(TelemetryRecord::getScore))
                .put("label", FieldComparatorBuilder.oneObjectFieldComparator(TelemetryRecord::getLabel))
                .put("status", FieldComparatorBuilder.oneObjectFieldComparator(TelemetryRecord::getStatus))
                .put("tags",
                        FieldComparatorBuilder.oneObjectFieldComparator(
                                TelemetryRecord::getTags,
                                new ArrayComparator<>(new OrderedArrayComparisonStrategy<>(), (ctx, s, t) -> Objects.equals(s, t))
                        )
                )
                .put("readings",
                        FieldComparatorBuilder.oneObjectFieldComparator(
                                TelemetryRecord::getReadings,
                                new CollectionComparator<>(new OrderedCollectionComparisonStrategy<>(), (ctx, s, t) -> Objects.equals(s, t))
                        )
                )
                .put("markers",
                        FieldComparatorBuilder.oneObjectFieldComparator(
                                TelemetryRecord::getMarkers,
                                new CollectionComparator<>(new UnorderedCollectionComparisonStrategy<>(), (ctx, s, t) -> Objects.equals(s, t))
                        )
                )
                .put("attributes",
                        FieldComparatorBuilder.oneObjectFieldComparator(
                                TelemetryRecord::getAttributes,
                                new MapComparator<>(new UnorderedMapComparisonStrategy<>(), (ctx, s, t) -> Objects.equals(s, t))
                        )
                )
                .put("snapshot",
                        FieldComparatorBuilder.of(TelemetryRecord.class, Snapshot.class, TelemetryRecord.class, Snapshot.class)
                                .setSourceExtractor(TelemetryRecord::getSnapshot)
                                .setTargetExtractor(TelemetryRecord::getSnapshot)
                                .setComparator(ObjectComparatorBuilder.of(Snapshot.class, Snapshot.class)
                                        .put("timestamp", FieldComparatorBuilder.oneObjectFieldComparator(Snapshot::getTimestamp))
                                        .put("checksum", FieldComparatorBuilder.oneObjectFieldComparator(Snapshot::getChecksum))
                                        .build()
                                )
                                .build()
                )
                .build();
    }

    private IObjectDiffCollector<TelemetryRecord, TelemetryRecord> createTelemetryDiffCollector() {
        return ObjectDiffCollectorBuilder.of(TelemetryRecord.class, TelemetryRecord.class)
                .put("active", FieldDiffCollectorBuilder.oneObjectFieldDiffCollector(TelemetryRecord::isActive))
                .put("retries", FieldDiffCollectorBuilder.oneObjectFieldDiffCollector(TelemetryRecord::getRetries))
                .put("score", FieldDiffCollectorBuilder.oneObjectFieldDiffCollector(TelemetryRecord::getScore))
                .put("label", FieldDiffCollectorBuilder.oneObjectFieldDiffCollector(TelemetryRecord::getLabel))
                .put("status", FieldDiffCollectorBuilder.oneObjectFieldDiffCollector(TelemetryRecord::getStatus))
                .put("tags", FieldDiffCollectorBuilder.of(TelemetryRecord.class, String[].class, TelemetryRecord.class, String[].class)
                        .setTargetExtractor(TelemetryRecord::getTags)
                        .setSourceExtractor(TelemetryRecord::getTags)
                        .setDiffCollector(ArrayDiffCollectorBuilder
                                .of(String.class, String.class)
                                .build())
                        .build()
                ).put("readings", FieldDiffCollectorBuilder.wrap(
                                TelemetryRecord::getReadings,
                                TelemetryRecord::getReadings,
                                CollectionDiffCollectorBuilder
                                        .of(Integer.class, Integer.class)
                                        .build()
                        )
                ).put("markers", FieldDiffCollectorBuilder.wrap(
                                TelemetryRecord::getMarkers,
                                TelemetryRecord::getMarkers,
                                CollectionDiffCollectorBuilder
                                        .of(String.class, String.class)
                                        .build()
                        )
                ).put("attributes",
                        FieldDiffCollectorBuilder.wrap(
                                TelemetryRecord::getAttributes,
                                TelemetryRecord::getAttributes,
                                MapDiffCollectorBuilder
                                        .of(String.class, Long.class, String.class, Long.class)
                                        .build()
                        )
                ).put("snapshot",
                        FieldDiffCollectorBuilder.of(TelemetryRecord.class, Snapshot.class, TelemetryRecord.class, Snapshot.class)
                                .setSourceExtractor(TelemetryRecord::getSnapshot)
                                .setTargetExtractor(TelemetryRecord::getSnapshot)
                                .setDiffCollector(ObjectDiffCollectorBuilder.of(Snapshot.class, Snapshot.class)
                                        .put("timestamp",
                                                FieldDiffCollectorBuilder.oneObjectFieldDiffCollector(Snapshot::getTimestamp))
                                        .put("checksum",
                                                FieldDiffCollectorBuilder.oneObjectFieldDiffCollector(Snapshot::getChecksum))
                                        .build())
                                .build()
                )
                .build();
    }

    private TelemetryRecord createBaselineRecord() {
        TelemetryRecord record = new TelemetryRecord();
        record.setActive(true);
        record.setRetries(3);
        record.setScore(95.5);
        record.setLabel("sensor-A");
        record.setStatus(ERecordStatus.PROCESSED);
        record.setTags(new String[]{"north", "stable", "blue"});
        record.setReadings(new ArrayList<>(Arrays.asList(100, 120, 130)));

        Set<String> markers = new LinkedHashSet<>();
        markers.add("CORE");
        markers.add("CANARY");
        record.setMarkers(markers);

        Map<String, Long> attributes = new LinkedHashMap<>();
        attributes.put("build", 10L);
        attributes.put("version", 1L);
        record.setAttributes(attributes);

        Snapshot snapshot = new Snapshot();
        snapshot.setTimestamp(1730000000L);
        snapshot.setChecksum("snapshot-1");
        record.setSnapshot(snapshot);

        return record;
    }

    private String diffToLog(IDiffNode node) {
        StringBuilder log = new StringBuilder();
        appendNode(log, node, 0);
        return log.toString();
    }

    private void appendNode(StringBuilder log, IDiffNode node, int depth) {
        if (node == null) return;

        if (node.getChildren().isEmpty()) {
            log.append("  ".repeat(depth))
                    .append(node.getPath())
                    .append(" | ")
                    .append(node.getSourceValue())
                    .append(" -> ")
                    .append(node.getTargetValue())
                    .append("\n");
        } else {
            for (IDiffNode child : node.getChildren()) {
                appendNode(log, child, depth + 1);
            }
        }
    }

}
