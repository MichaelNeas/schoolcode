SELECT 
    COUNT(*) AS numberOfCustomers
FROM
    Customers
WHERE
    country != 'null'
GROUP BY Country;
