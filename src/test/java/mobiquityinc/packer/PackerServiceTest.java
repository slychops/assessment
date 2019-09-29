package mobiquityinc.packer;

import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class PackerServiceTest {

    @Test
    public void doNothingTest() {
        PackerService packerService = new PackerService();
    }

    @Test
    public void getValidStringReturnedForOnePackage() {
        PackerService packerService = new PackerService();

        PackageItem item1 = new PackageItem(8, new BigDecimal("19.36"), new BigDecimal("79"));
        PackageItem item2 = new PackageItem(9, new BigDecimal("6.76"), new BigDecimal("64"));
        Package aPackage = new Package(new BigDecimal("56"), Arrays.asList(item1, item2));

        assertEquals("8, 9", packerService.optimizedPackageToString(aPackage).toString());
        assertNotEquals("234, 25jlk23j", packerService.optimizedPackageToString(aPackage));
    }


}
