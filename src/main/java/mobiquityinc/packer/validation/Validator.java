package mobiquityinc.packer.validation;

import mobiquityinc.packer.exception.APIException;
import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;

import java.math.BigDecimal;

import static mobiquityinc.packer.exception.APIException.*;

public class Validator {

    private static final BigDecimal MAX_PACKAGE_WEIGHT = new BigDecimal(100);
    private static final int MAX_PACKAGE_SIZE = 15;
    private static final BigDecimal MAX_ITEM_WEIGHT = new BigDecimal(100);
    private static final BigDecimal MAX_ITEM_COST = new BigDecimal(100);

    public void checkPackage(Package aPackage) {

        checkCondition(aPackage.getWeight().compareTo(MAX_PACKAGE_WEIGHT) <= 0, "Maximum package weight too large.");
        checkCondition(aPackage.getItems().size() <= MAX_PACKAGE_SIZE, "Maximum package size exceeded.");

        for (PackageItem item: aPackage.getItems()) {
            checkItem(item);
        }
    }

    public void checkItem(PackageItem item) {

        checkCondition(item.getWeight().compareTo(MAX_ITEM_WEIGHT) <= 0, "Maximum item weight too large.");
        checkCondition(item.getCost().compareTo(MAX_ITEM_COST) <= 0, "Maximum item cost too large.");
    }
}
