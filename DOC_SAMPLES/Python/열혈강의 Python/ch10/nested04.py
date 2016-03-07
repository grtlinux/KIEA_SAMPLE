# nested04.py
def bank_account1(initial_balance): 
    balance = initial_balance 
    def deposit(amount): 
        balance = balance + amount 
        return balance 
    def withdraw(amount): 
        balance = balance - amount 
        return balance 
    return deposit, withdraw

d, w = bank_account1(100)
print d(100)
