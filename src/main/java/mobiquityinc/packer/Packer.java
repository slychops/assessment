package mobiquityinc.packer;

import mobiquityinc.packer.exception.APIException;

public class Packer {
    private static PackerService service = new PackerService();

    public static String pack(String filePath) throws APIException {

        service.packPackage(filePath);
        return null;
    }
}
