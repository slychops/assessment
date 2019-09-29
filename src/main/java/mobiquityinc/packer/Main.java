package mobiquityinc.packer;

import mobiquityinc.packer.model.Package;

public class Main {
    public static void main(String[] args) {
        Packer.pack("src/test/resources/sample-input");
//        Packer.pack("src/test/resources/fail-package"); //Fail package weight
//        Packer.pack("src/test/resources/fail-too-many-items-in-package"); //Fail items > 15
//        Packer.pack("src/test/resources/fail-items-on-value");
//        Packer.pack("src/test/resources/fail-items-on-weight");

//        Packer.pack("src/test/resources/wrong-input");
//        System.out.println(Packer.pack("src/test/resources/sample-input"));
    }
}
