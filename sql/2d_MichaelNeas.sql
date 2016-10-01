SELECT 
    ProductName, UnitPrice, UnitsInStock
FROM
    Products
WHERE
    ProductName LIKE 'Ch%'
ORDER BY ProductName;