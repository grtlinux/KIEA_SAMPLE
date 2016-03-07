# nested05.py
def bank_account2(initial_balance): 
    balance = [initial_balance] 
    def deposit(amount): 
        balance[0] = balance[0] + amount 
        return balance[0]
    def withdraw(amount): 
        balance[0] = balance[0] - amount 
        return balance[0]
    return deposit, withdraw 

d, w = bank_account2(100)
print d(100)
