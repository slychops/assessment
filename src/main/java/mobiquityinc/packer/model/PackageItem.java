package mobiquityinc.packer.model;

import java.math.BigDecimal;
import java.util.Objects;

public class PackageItem implements Comparable<PackageItem> {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageItem item = (PackageItem) o;
        return index == item.index &&
                weight.equals(item.weight) &&
                cost.equals(item.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, weight, cost);
    }

    @Override
    public String toString() {
        return "PackageItem{" +
                "index=" + index +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }

    @Override
    public int compareTo(PackageItem item) {
        PackageItem compareItem = ((PackageItem) item);

        return (this.weight.intValue() - compareItem.weight.intValue());
    }
}
