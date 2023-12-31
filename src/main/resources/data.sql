-- CUSTOMER TABLE
INSERT INTO customer(id, name) VALUES(1, 'Paksi');

-- PAYMENT TYPE TABLE
INSERT INTO payment_type(id, name) VALUES(1, 'Bank Transfer');

-- INVENTORY TABLE
INSERT INTO inventory(id, name, quantity, price) VALUES(1, 'Gantungan kunci', 100, 5000);
INSERT INTO inventory(id, name, quantity, price) VALUES(2, 'Gantungan baju', 50, 7500);

-- PAYMENT TABLE
INSERT INTO public.payment (amount,customer_id,"date",payment_type_id) VALUES
	 (5000.0,1,'2023-08-25 07:11:34.436',1),
	 (10000.0,1,'2023-08-25 07:11:34.436',1),
	 (17500.0,1,'2023-08-26 07:11:34.436',1),
	 (47500.0,1,'2023-08-31 07:11:34.436',1);

-- PAYMENT INVENTORY TABLE
INSERT INTO public.payment_inventory (quantity,total_price,inventory_id,payment_id) VALUES
	 (1,5000.0,1,1),
	 (2,10000.0,1,2),
	 (2,10000.0,1,3),
	 (1,7500.0,2,3),
	 (5,25000.0,1,4),
	 (3,22500.0,2,4);