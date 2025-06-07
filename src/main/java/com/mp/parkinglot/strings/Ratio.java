package com.mp.parkinglot.strings;

public enum Ratio {
    EMPTY(0, 0), PLENTY(0, 30), MODERATE(31, 70), BUSY(71, 99), FULL(100, 100);

    private Integer minRatio;
    private Integer maxRatio;

    Ratio(Integer minRatio, Integer maxRatio) {
        this.minRatio = minRatio;
        this.maxRatio = maxRatio;
    }

    static public Ratio getRatio(Long filled, Long compacity) {
        Long ratio = filled / compacity * 100;
        if (ratio < 0) return EMPTY;
        if (ratio <= 30) return PLENTY;
        if (ratio <= 70) return MODERATE;
        if (ratio < 100) return BUSY;
        return FULL;
    }
}
