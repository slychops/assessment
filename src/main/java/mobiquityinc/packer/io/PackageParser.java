package mobiquityinc.packer.io;

import mobiquityinc.packer.model.Package;
import mobiquityinc.packer.model.PackageItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static mobiquityinc.packer.exception.APIException.checkCondition;

public class PackageParser {
    private static final Pattern FULL_LINE = Pattern.compile("^(?:(\\d+(?:\\.\\d+)?)\\s?:\\s?)(\\((\\d+),(\\d+(?:\\.\\d+)?),.(\\d+(?:\\.\\d+)?)\\)\\s?)*$");
    private static final Pattern PACKAGE_WEIGHT = Pattern.compile("(?:(\\d+(?:\\.\\d+)?)\\s?:\\s?).*");
    private static final Pattern PACKAGE_ITEM = Pattern.compile("(?:\\((\\d+),(\\d+(?:\\.\\d+)?),.(\\d+(?:\\.\\d+)?)\\)\\s?)");


    public Package parseLine(String line) {

        checkCondition(FULL_LINE.asMatchPredicate().test(line), "Line does not match expected pattern.");

        Package aPackage = parsePackage(line);
        aPackage.getItems().addAll(parseItems(line));

        Collections.sort(aPackage.getItems()); //This is so that when we run our Knapsack algorithm it returns us the lightest possible outcome
        return aPackage;
    }

    private List<PackageItem> parseItems(String line) {
        Matcher itemMatcher = PACKAGE_ITEM.matcher(line);
        List<PackageItem> items = new ArrayList<>();

        while (itemMatcher.find()) {
            Integer index = Integer.valueOf(itemMatcher.group(1));
            BigDecimal weight = new BigDecimal(itemMatcher.group(2));
            BigDecimal cost = new BigDecimal(itemMatcher.group(3));

            PackageItem item = new PackageItem(index, weight, cost);
            items.add(item);
        }
        return items;
    }

    private Package parsePackage(String line) {
        Matcher matcher = PACKAGE_WEIGHT.matcher(line);
        matcher.find();

        String weightString = matcher.group(1);
        return new Package(new BigDecimal(weightString), new ArrayList<>());
    }


}
