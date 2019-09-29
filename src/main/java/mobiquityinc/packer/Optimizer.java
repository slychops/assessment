package mobiquityinc.packer;

import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Optimizer{
    private static final BigDecimal MULTIPLIER = new BigDecimal( "100" );

    Optimizer() {
    }

    Package packPackage( Package aPackage ) {
        int nItems = aPackage.getItems().size();
        int maxPackageWeight = aPackage.getWeight().multiply( MULTIPLIER ).intValue();

        int[][] matrix = createOptimizationMatrix( aPackage, nItems, maxPackageWeight );

        return optimizePackage( aPackage, matrix, nItems, maxPackageWeight );
    }

    private int[][] createOptimizationMatrix( Package aPackage, int nItems, int maxPackageWeight ) {
        int[][] matrix = new int[nItems + 1][maxPackageWeight + 1];

        for ( int i = 0; i < maxPackageWeight + 1; i++ ) {
            matrix[0][i] = 0;
        }

        for ( int i = 1; i <= nItems; i++ ) {

            int weightOfCurrentItem = aPackage.getItems().get( i - 1 ).getWeight().multiply( MULTIPLIER ).intValue();

            for ( int j = 0; j <= maxPackageWeight; j++ ) {

                if ( weightOfCurrentItem > j ) {
                    matrix[i][j] = matrix[i - 1][j];
                }
                else {
                    matrix[i][j] = Math.max( matrix[i - 1][j]
                            , matrix[i - 1][j - weightOfCurrentItem] + aPackage.getItems().get( i - 1 ).getCost().intValue() );
                }
            }
        }
        return matrix;
    }

    private Package optimizePackage( Package aPackage, int[][] matrix, int nItems, int maxPackageWeight ) {
        int res = matrix[nItems][maxPackageWeight];
        int w = maxPackageWeight;

        List<PackageItem> optimizedItems = new ArrayList<>();

        for ( int i = nItems; i > 0  &&  res > 0; i-- ) {

            PackageItem item = aPackage.getItems().get(i - 1);

            if ( res != matrix[i-1][w] ) {
                optimizedItems.add(item);

                res -= item.getCost().intValue();
                w -= item.getWeight().multiply( MULTIPLIER ).intValue();
            }
        }

        return new Package(aPackage.getWeight(), sortPackageByIndex( optimizedItems ));
    }

    private List<PackageItem> sortPackageByIndex( List<PackageItem> optimizedItems ) {
        optimizedItems.sort( Comparator.comparingInt( PackageItem::getIndex ));

        return optimizedItems;
    }
}
