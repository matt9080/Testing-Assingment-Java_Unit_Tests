package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;

import java.util.List;

public interface CurrencyDatabaseInterface {
    void setCurrencies(List<Currency> currencies);

    void initilizeList();

    void setPath(String path);

    void setReaderWriter(ReaderWriter readerWriter);

    Currency getCurrencyByCode(String code);

    boolean currencyExists(String code);

    List<Currency> getCurrencies();

    List<Currency> getMajorCurrencies();

    void setCurrencyServer(CurrencyServer currencyServer);

    ExchangeRate getExchangeRate(String sourceCurrencyCode, String destinationCurrencyCode) throws  Exception;

    void addCurrency(Currency currency) throws Exception;

    void deleteCurrency(String code) throws Exception;
}
