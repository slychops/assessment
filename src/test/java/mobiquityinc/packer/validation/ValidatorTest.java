package mobiquityinc.packer.validation;

import mobiquityinc.packer.exception.APIException;
import mobiquityinc.packer.model.Package;
import static org.junit.jupiter.api.Assertions.*;

import mobiquityinc.packer.model.PackageItem;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


//Todo Mockito

public class ValidatorTest {

    public Package generatePackage() {
        List<PackageItem> items = new ArrayList<>();

        for (int i = 0; i < 3; i ++) {
            items.add(generateItem());
        }

        return new Package(new BigDecimal(80), items);
    }

    public PackageItem generateItem() {
        int index = 1;
        BigDecimal weight = new BigDecimal(53.58);
        BigDecimal cost = new BigDecimal(45);

        return new PackageItem(index, weight, cost);
    }

    @Test
    public void testPackageValid(){
        Validator validator = new Validator();

        Package aPackage = generatePackage();

        validator.checkPackage(aPackage);
    }

    @Test
    public void testMaximumPackageWeightInvalid(){
        Validator validator = new Validator();
        List<PackageItem> items = new ArrayList<>();

        BigDecimal maxValue = new BigDecimal(101);
        items.add(generateItem());

        Package aPackage = new Package(maxValue, items);

        assertThrows(APIException.class, () -> validator.checkPackage(aPackage));
    }

    @Test
    public void testMaximumItemLimitInvalid() {
        Validator validator = new Validator();
        Package aPackage = generatePackage();

        while (aPackage.getItems().size() < 16) {
            aPackage.getItems().add(generateItem());
        }

        assertThrows(APIException.class, () -> validator.checkPackage(aPackage));
    }

    @Test
    public void testItemValid() {
        Validator validator = new Validator();

        PackageItem item = generateItem();

        validator.checkItem(item);
    }

    @Test
    public void testMaximumWeightOfItemInvalid() {
        Validator validator = new Validator();
        int index = 1;
        BigDecimal weight = new BigDecimal(100.13);
        BigDecimal cost = new BigDecimal(45);

        PackageItem item = new PackageItem(index, weight, cost);

        assertThrows(APIException.class, () -> validator.checkItem(item));
    }

    @Test
    public void testMaximumCostOfItemInvalid() {
        Validator validator = new Validator();
        int index = 1;
        BigDecimal weight = new BigDecimal(80.7);
        BigDecimal cost = new BigDecimal(101.32);

        PackageItem item = new PackageItem(index, weight, cost);

        assertThrows(APIException.class, () -> validator.checkItem(item));
    }

    @Test
    public void testItemsInPackageContainsInvalid() {
        Validator validator = new Validator();
        Package aPackage = generatePackage();

        int index = 1;
        BigDecimal weight = new BigDecimal(80.7);
        BigDecimal cost = new BigDecimal(101.32);

        aPackage.getItems().add(new PackageItem(index, weight, cost));

        assertThrows(APIException.class, () -> validator.checkPackage(aPackage));
    }

}
