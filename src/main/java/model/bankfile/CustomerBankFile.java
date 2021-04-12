package model.bankfile;

import config.Configurer;


public class CustomerBankFile extends BankFile {

    public CustomerBankFile(String path) {
        super(path, Configurer.getConfiguredClientPropertiesProcessor());
    }

    @Override
    public String getAbbreviation() {
        return "CI";
    }
}
