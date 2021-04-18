package model.bankfile;

import config.Configurer;
import repository.AccountRepository;

public class AccountFileData extends FileData{
    public AccountFileData(BankFile bankFile) {
        super(bankFile, Configurer.getInstance().getConfiguredAccountStringProcessor());
    }

    @Override
    public void save() {
        processedData.forEach(AccountRepository.getInstance()::save);
    }
}
