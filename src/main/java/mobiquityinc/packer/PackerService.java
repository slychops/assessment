package mobiquityinc.packer;

import mobiquityinc.packer.io.FileReader;
import mobiquityinc.packer.io.PackageParser;
import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;

import java.math.BigDecimal;
import java.util.*;

public class PackerService {
    private FileReader reader;
    private PackageParser parser;
    private List<Package> packages;
    private Optimizer optimizer;

//    static final BigDecimal MULTIPLIER = new BigDecimal("100");

    public PackerService() {
        this.reader = new FileReader();
        this.parser  = new PackageParser();
        this.packages = new ArrayList<>();
        this.optimizer = new Optimizer();
    }

    public String packPackage(String filePath) {
        String stringToReturn = "";
        StringBuilder packagesToString;

        parseLine(filePath);

//        getOptimizedPackage();



        for (Package aPackage: packages) {

            packagesToString = new StringBuilder();
//            for (PackageItem item: packPackage(aPackage).getItems()) {
            for (PackageItem item: optimizer.packPackage(aPackage).getItems()) {
                packagesToString.append(item.getIndex());
                packagesToString.append(", ");
            }

            if (packagesToString.lastIndexOf(", ") < 0) {
                packagesToString.append("-");
            } else if (packagesToString.lastIndexOf(", ") > 0) {
                packagesToString.replace(packagesToString.lastIndexOf(", "), packagesToString.lastIndexOf(", ") + 2, "");
            }
            System.out.println(packagesToString.toString());

        }
        return stringToReturn;
    }

    /*Parses and packages all file data*/
    private void parseLine(String filePath) {
        for (String s: reader.getFile(filePath)) {
            packages.add(parser.parseLine(s));
        }
    }


//    public Package packPackage(Package unsortedPackage) {
//        int nItems = unsortedPackage.getItems().size();
//        int maxPackageWeight = unsortedPackage.getWeight().multiply(MULTIPLIER).intValue();
//
//        int[][] matrix = new int[nItems + 1][maxPackageWeight + 1];
//
//        for (int i = 0; i < maxPackageWeight + 1; i++) {
//            matrix[0][i] = 0;
//        }
//
//        for (int i = 1; i <= nItems; i++) {
//
//            int weightOfCurrentItem = unsortedPackage.getItems().get(i - 1).getWeight().multiply(MULTIPLIER).intValue();
//
//            for (int j = 0; j <= maxPackageWeight; j++) {
//
//                if (weightOfCurrentItem > j) {
//                    matrix[i][j] = matrix[i - 1][j];
//                }
//                else {
//                    matrix[i][j] = Math.max(matrix[i - 1][j]
//                            , matrix[i - 1][j - weightOfCurrentItem] + unsortedPackage.getItems().get(i - 1).getCost().intValue());
//                }
//            }
//        }
//
//        int res = matrix[nItems][maxPackageWeight];
//        int w = maxPackageWeight;
//
//        List<PackageItem> itemsSolution = new ArrayList<>();
//
//        for (int i = nItems; i > 0  &&  res > 0; i--) {
//
//            PackageItem item = unsortedPackage.getItems().get(i - 1);
//
//            if (res != matrix[i-1][w]) {
//                itemsSolution.add(item);
//
//                res -= item.getCost().intValue();
//                w -= item.getWeight().multiply(MULTIPLIER).intValue();
//            }
//        }
//
//        /*Resorts the package according to index before passing it back*/
//        itemsSolution.sort(Comparator.comparingInt(PackageItem::getIndex));
//        return new Package(unsortedPackage.getWeight(), itemsSolution);
//    }


}
