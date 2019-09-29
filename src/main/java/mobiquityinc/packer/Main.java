package mobiquityinc.packer;

import mobiquityinc.packer.model.Package;

public class Main {
    public static void main(String[] args) {
        Packer.pack("src/test/resources/sample-input");
//        Packer.pack("src/test/resources/wrong-input");
//        System.out.println(Packer.pack("src/test/resources/sample-input"));
    }
}
