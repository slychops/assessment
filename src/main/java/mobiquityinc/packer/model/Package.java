package mobiquityinc.packer.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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



}
