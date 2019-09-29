package mobiquityinc.packer;

import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Optimizer {
    static final BigDecimal MULTIPLIER = new BigDecimal("100");

    public Optimizer() {
    }

    public Package packPackage(Package unsortedPackage) {
        int nItems = unsortedPackage.getItems().size();
        int maxPackageWeight = unsortedPackage.getWeight().multiply(MULTIPLIER).intValue();

        int[][] matrix = new int[nItems + 1][maxPackageWeight + 1];

        for (int i = 0; i < maxPackageWeight + 1; i++) {
            matrix[0][i] = 0;
        }

        for (int i = 1; i <= nItems; i++) {

            int weightOfCurrentItem = unsortedPackage.getItems().get(i - 1).getWeight().multiply(MULTIPLIER).intValue();

            for (int j = 0; j <= maxPackageWeight; j++) {

                if (weightOfCurrentItem > j) {
                    matrix[i][j] = matrix[i - 1][j];
                }
                else {
                    matrix[i][j] = Math.max(matrix[i - 1][j]
                            , matrix[i - 1][j - weightOfCurrentItem] + unsortedPackage.getItems().get(i - 1).getCost().intValue());
                }
            }
        }

        int res = matrix[nItems][maxPackageWeight];
        int w = maxPackageWeight;

        List<PackageItem> itemsSolution = new ArrayList<>();

        for (int i = nItems; i > 0  &&  res > 0; i--) {

            PackageItem item = unsortedPackage.getItems().get(i - 1);

            if (res != matrix[i-1][w]) {
                itemsSolution.add(item);

                res -= item.getCost().intValue();
                w -= item.getWeight().multiply(MULTIPLIER).intValue();
            }
        }

        /*Resorts the package according to index before passing it back*/
        itemsSolution.sort(Comparator.comparingInt(PackageItem::getIndex));
        return new Package(unsortedPackage.getWeight(), itemsSolution);
    }

    private void createMatrixForPackage(Package aPackage) {

    }

    private Package buidlPackage(Package aPackage) {

        return aPackage;
    }
}
