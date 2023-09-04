CREATE OR REPLACE VIEW vpayment_inventories AS
SELECT 
        gen_random_uuid() as id,
        p.id AS "payment_id", 
        c.name AS "customer_name", 
        p.amount, 
        i.name AS "inventory_id", 
        pt.name AS "payment_type_name", 
        p.date 
FROM payment_inventory pi 
INNER JOIN payment p ON p.id = pi.payment_id 
INNER JOIN inventory i ON i.id = pi.inventory_id 
INNER JOIN customer c ON c.id = p.customer_id 
INNER JOIN payment_type pt ON pt.id = p.payment_type_id;