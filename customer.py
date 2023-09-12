class Customer:
	def __init__(self, customer_id, first_name, last_name, age, address, phone, email):
		self.customer_id = customer_id
		self.first_name = first_name
		self.last_name = last_name
		self.age = age
		self.address = address
		self.phone = phone
		self.email = email

	def __str__(self):
		return (f'(Customer) #{self.customer_id} {self.first_name} {self.last_name}, {self.age} y.o. lives at {self.address}, '
		        f'\n\tContacts: {self.phone}, {self.email}')
