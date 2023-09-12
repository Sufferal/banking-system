class Bank:
  def __init__(self, name):
    self.name = name
    self.customers = []

  def add_customer(self, customer):
    self.customers.append(customer)

  def __str__(self):
    return f'Bank {self.name} has {len(self.customers)} customers'