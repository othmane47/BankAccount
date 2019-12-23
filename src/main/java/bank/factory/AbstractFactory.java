package bank.factory;


import bank.model.Account;

public interface AbstractFactory<T> {
    T create(String operation, double value, Account from, Account to) ;
    T create(String operation, double value) ;
}
