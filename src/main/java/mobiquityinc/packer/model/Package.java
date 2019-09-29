package mobiquityinc.packer.model;

import java.math.BigDecimal;
import java.util.*;

public class Package {

    private final BigDecimal weight;
    private final List<PackageItem> items;

    public Package(BigDecimal weight, List<PackageItem> items) {

        this.weight = weight;
        this.items = items;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public List<PackageItem> getItems() {
        return items;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return weight.equals(aPackage.weight) &&
                items.equals(aPackage.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, items);
    }

    @Override
    public String toString() {
        return "Package{" +
                "weight=" + weight +
                ", items=" + items +
                '}';
    }
}
