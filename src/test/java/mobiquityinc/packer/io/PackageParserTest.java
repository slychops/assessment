package mobiquityinc.packer.io;

import mobiquityinc.packer.exception.APIException;
import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PackageParserTest {

    @Test
    public void isLineValid() {
        PackageParser parser = new PackageParser();
        String line = "81.92 : (10,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

        parser.parseLine(line);
    }

    @Test
    public void lineIsInvalid() {
        PackageParser parser = new PackageParser();
        String line = "81.92 sdsdfsdfsdff: (10,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

        assertThrows(APIException.class, () -> parser.parseLine(line));
    }

    @Test
    public void parsePackageWeight() {
        PackageParser parser = new PackageParser();
        String line = "81.92 : (10,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

        BigDecimal packageWeight = parser.parseLine(line).getWeight();

        assertEquals(new BigDecimal("81.92"), packageWeight);
    }

    @Test
    public void parsePackageItem() {
        PackageParser parser = new PackageParser();

        String line = "81.92 : (10,53.38,€45) (2,88.62,€98)";

        Package aPackage = parser.parseLine(line);
        List<PackageItem> items = Arrays.asList(
                new PackageItem(10, new BigDecimal("53.38"),new BigDecimal("45")),
                new PackageItem(2, new BigDecimal("88.62"), new BigDecimal("98")));

        assertEquals(items, aPackage.getItems());
    }

    @Test
    public void testListIsOrderedInAscendingWeight() {
        PackageParser parser = new PackageParser();

        String line = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";

        PackageItem item1 = new PackageItem(1, new BigDecimal("90.72"), new BigDecimal("13"));
        PackageItem item2 = new PackageItem(2, new BigDecimal("33.80"), new BigDecimal("40"));
        PackageItem item3 = new PackageItem(3, new BigDecimal("43.15"), new BigDecimal("10"));
        PackageItem item4 = new PackageItem(4, new BigDecimal("37.97"), new BigDecimal("16"));
        PackageItem item5 = new PackageItem(5, new BigDecimal("46.81"), new BigDecimal("36"));
        PackageItem item6 = new PackageItem(6, new BigDecimal("48.77"), new BigDecimal("79"));
        PackageItem item7 = new PackageItem(7, new BigDecimal("81.80"), new BigDecimal("45"));
        PackageItem item8 = new PackageItem(8, new BigDecimal("19.36"), new BigDecimal("79"));
        PackageItem item9 = new PackageItem(9, new BigDecimal("6.76"), new BigDecimal("64"));

        List<PackageItem> expectedList = Arrays.asList(item9, item8, item2, item4, item3, item5, item6, item7, item1);

        List<PackageItem> sortedList = parser.parseLine(line).getItems();

        assertEquals(expectedList, sortedList);

    }

}
