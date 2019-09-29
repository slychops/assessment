package mobiquityinc.packer;

import mobiquityinc.packer.io.FileReader;
import mobiquityinc.packer.io.PackageParser;
import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;
import mobiquityinc.packer.validation.Validator;

import java.util.*;

public class PackerService {
    private FileReader reader;
    private PackageParser parser;
    private List<Package> packages;
    private Optimizer optimizer;
    private Validator validator;

//    static final BigDecimal MULTIPLIER = new BigDecimal("100");

    public PackerService() {
        this.reader = new FileReader();
        this.parser  = new PackageParser();
        this.packages = new ArrayList<>();
        this.optimizer = new Optimizer();
        this.validator = new Validator();
    }

    public String packPackage(String filePath) {

        parseLine(filePath);

        checkPackages(packages);

        return getOptimizedPackage(packages);
    }

    /*Parses and packages all file data*/
    private void parseLine(String filePath) {
        for (String s: reader.getFile(filePath)) {
            packages.add(parser.parseLine(s));
        }
    }

    private void checkPackages(List<Package> packages) {
        for (Package aPackage: packages) {
            validator.checkPackage(aPackage);
        }
    }

    /*Returns the optimized packages as a single string*/
    private String getOptimizedPackage(List<Package> packages) {
        StringBuilder stringToReturn = new StringBuilder();

        for (Package aPackage: packages) {
            stringToReturn.append(optimizedPackageToString(aPackage)).append("\n");
        }

        return stringToReturn.toString().trim();
    }

    StringBuilder optimizedPackageToString(Package aPackage) {
        StringBuilder packageToString = new StringBuilder();

        for (PackageItem item: optimizer.packPackage(aPackage).getItems()) {
            packageToString.append(item.getIndex());
            packageToString.append(", ");
        }

        if (packageToString.lastIndexOf(", ") < 0) {
            packageToString.append("-");
        } else if (packageToString.lastIndexOf(", ") > 0) {
            packageToString.replace(packageToString.lastIndexOf(", "), packageToString.lastIndexOf(", ") + 2, "");
        }

        return packageToString;
    }

}
