package com.gildedrose;

import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.legacycode.Range;
import org.approvaltests.reporters.UseReporter;
import org.approvaltests.reporters.intellij.IntelliJReporter;
import org.junit.jupiter.api.Test;

@UseReporter(IntelliJReporter.class)
public class GildedRoseApprovalTest {
    @Test
    void testAllCombinations() {

        String[] items = { "Foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert",
                "Sulfuras, Hand of Ragnaros", "Conjured" };
        Integer[] sellins = Range.get(-1, 100);
        Integer[] qualities = Range.get(-1, 100);
        CombinationApprovals.verifyAllCombinations(this::checkItem, items, sellins, qualities);
    }

    public Item checkItem(String item, Integer sellin, Integer quality) {
        Item[] items = new Item[] { new Item(item, sellin, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0];
    }
}
