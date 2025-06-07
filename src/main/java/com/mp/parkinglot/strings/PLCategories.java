package com.mp.parkinglot.strings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PLCategories {
    private Boolean bathroom;
    private Boolean wide;
    private Boolean charger;

    public PLCategories(boolean bathroom, boolean wide, boolean charger) {
        this.bathroom = bathroom;
        this.wide = wide;
        this.charger = charger;
    }
}
