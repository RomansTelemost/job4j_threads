package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {

    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        if (accounts.containsKey(account.id())) {
            return false;
        }
        accounts.put(account.id(), account);
        return true;
    }

    public synchronized boolean update(Account account) {
        if (accounts.containsKey(account.id())) {
            accounts.put(account.id(), account);
            return true;
        }
        return false;
    }

    public synchronized boolean delete(int id) {
        if (accounts.containsKey(id)) {
            accounts.remove(id);
            return true;
        }
        return false;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> fromAccount = getById(fromId);
        Optional<Account> toAccount = getById(toId);

        if (fromAccount.isEmpty() || toAccount.isEmpty()) {
            return false;
        }

        if (fromAccount.get().amount() < amount) {
            return false;
        }
        Account newFromAccount = new Account(fromAccount.get().id(), fromAccount.get().amount() - amount);
        Account newToAccount = new Account(toAccount.get().id(), toAccount.get().amount() + amount);
        accounts.remove(fromId);
        accounts.remove(toId);
        accounts.put(newFromAccount.id(), newFromAccount);
        accounts.put(newToAccount.id(), newToAccount);

        return true;
    }
}
