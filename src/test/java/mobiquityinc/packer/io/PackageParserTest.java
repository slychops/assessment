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

}
