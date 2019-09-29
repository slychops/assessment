package mobiquityinc.packer;

import mobiquityinc.packer.exception.APIException;

public abstract class Packer{
    private static PackerService service = new PackerService();

    public static String pack(String filePath) throws APIException {

        System.out.println(service.packPackage(filePath));

        return service.packPackage(filePath);
    }
}
