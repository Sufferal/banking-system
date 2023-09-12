import customer, bank

if __name__ == '__main__':
    b1 = bank.Bank('Bank of America')
    c1 = customer.Customer(1, 'John', 'Smith', 20, '123 Main St', '123-456-7890', 'john@doecom')
    c2 = customer.Customer(2, 'Jane', 'Doe', 21, '123 Main St', '123-456-7890', 'jane@doecom')
    print(c1)
    print(c2)
    b1.add_customer(c1)
    b1.add_customer(c2)
    print(b1)









