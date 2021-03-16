package com.jitterted;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryLevelTest {

    @Test
    void testLevelForInventory() {
       InventoryLevel inventoryLevel = new InventoryLevel(10);
       assertThat(inventoryLevel.level()).isEqualTo(10);
    }


}
