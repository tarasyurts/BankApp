package model.bankfile;

import config.Configurer;

public class AccountBankFile extends BankFile{
    public AccountBankFile(String path) {
        super(path, Configurer.getInstance().getConfiguredAccountStringProcessor());
    }
}
