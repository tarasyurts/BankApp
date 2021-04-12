package model.bankfile;

import config.Configurer;

public class AccountBankFile extends BankFile{
    public AccountBankFile(String path) {
        super(path, Configurer.getConfiguredAccountPropertiesProcessor());
    }

    @Override
    public String getAbbreviation() {
        return "AI";
    }
}
