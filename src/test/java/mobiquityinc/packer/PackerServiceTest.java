package mobiquityinc.packer;

import mobiquityinc.packer.io.PackageParser;
import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackerServiceTest {

    @Test
    public void doNothingTest() {
        PackerService packerService = new PackerService();
    }

    @Test
    public void calculateOptimalPackage(){
        PackerService packerService = new PackerService();
        PackageItem item1 = new PackageItem(1, new BigDecimal("53.38"),new BigDecimal("45"));
        PackageItem item2 = new PackageItem(2, new BigDecimal("10.62"), new BigDecimal("12"));
        PackageItem item3 = new PackageItem(3, new BigDecimal("88.62"), new BigDecimal("98"));

        Package unsortedPackage = new Package(new BigDecimal("70"), Arrays.asList(item1, item2, item3));
        Package expectedPackage = new Package(new BigDecimal("70"), Arrays.asList(item1, item2));

        Package optimalPackage = packerService.packPackage(unsortedPackage);

        assertEquals(expectedPackage, optimalPackage);


    }


}
