package mobiquityinc.packer.model;

import java.math.BigDecimal;

public class PackageItem {

    private final int index;
    private final BigDecimal weight;
    private final BigDecimal cost;

    public int getIndex() {
        return index;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public PackageItem(int index, BigDecimal weight, BigDecimal cost) {

        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }
}
