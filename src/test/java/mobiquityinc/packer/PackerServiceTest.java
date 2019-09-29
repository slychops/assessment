package mobiquityinc.packer;

import mobiquityinc.packer.io.FileReader;
import mobiquityinc.packer.io.PackageParser;
import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackerServiceTest {

    @Test
    public void doNothingTest() {
        PackerService packerService = new PackerService();
    }

//    @Test
//    public void calculateOptimalPackage(){
//        PackerService packerService = new PackerService();
//        PackageItem item1 = new PackageItem(1, new BigDecimal("53.38"),new BigDecimal("45"));
//        PackageItem item2 = new PackageItem(2, new BigDecimal("10.62"), new BigDecimal("12"));
//        PackageItem item3 = new PackageItem(3, new BigDecimal("88.62"), new BigDecimal("98"));
//
//        Package unsortedPackage = new Package(new BigDecimal("70"), Arrays.asList(item1, item2, item3));
//        Package expectedPackage = new Package(new BigDecimal("70"), Arrays.asList(item1, item2));
//
//        Package optimalPackage = packerService.packPackage(unsortedPackage);
//
//        assertEquals(expectedPackage, optimalPackage);
//    }
//
//    @Test
//    public void testOptimalPackageHasLowestWeight() {
//        PackerService packerService = new PackerService();
//
//        PackageItem item1 = new PackageItem(1, new BigDecimal("90.72"), new BigDecimal("13"));
//        PackageItem item2 = new PackageItem(2, new BigDecimal("33.80"), new BigDecimal("40"));
//        PackageItem item3 = new PackageItem(3, new BigDecimal("43.15"), new BigDecimal("10"));
//        PackageItem item4 = new PackageItem(4, new BigDecimal("37.97"), new BigDecimal("16"));
//        PackageItem item5 = new PackageItem(5, new BigDecimal("46.81"), new BigDecimal("36"));
//        PackageItem item6 = new PackageItem(6, new BigDecimal("48.77"), new BigDecimal("79"));
//        PackageItem item7 = new PackageItem(7, new BigDecimal("81.80"), new BigDecimal("45"));
//        PackageItem item8 = new PackageItem(8, new BigDecimal("19.36"), new BigDecimal("79"));
//        PackageItem item9 = new PackageItem(9, new BigDecimal("6.76"), new BigDecimal("64"));
//
//        List<PackageItem> reverseSorted = Arrays.asList(item9, item8, item2, item4, item3, item5, item6, item7, item1);
//
//        Package aPackage = new Package(new BigDecimal("56"), reverseSorted);
//        Package expectedPackage = new Package(new BigDecimal("56"), Arrays.asList(item8, item9));
//
//        assertEquals(expectedPackage, packerService.packPackage(aPackage));
//    }



}
