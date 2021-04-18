package model.bankfile;

import config.Configurer;
import repository.CustomerRepository;

public class CustomerFileData extends FileData {
    public CustomerFileData(BankFile bankFile) {
        super(bankFile, Configurer.getInstance().getConfiguredClientStringProcessor());
    }

    @Override
    public void save() {
        processedData.forEach(CustomerRepository.getInstance()::save);
    }
}
