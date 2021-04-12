package file;

import config.Configurer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClientBankFile extends BankFile {

    public ClientBankFile(String path) {
        super(path, Configurer.getConfiguredClientPropertiesProcessor());
    }

    @Override
    public String getAbbreviation() {
        return "CI";
    }
}
