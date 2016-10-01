SELECT 
    *
FROM
    (SELECT 
        ProductID,
            ProductName,
            UnitsInStock,
            ReorderLevel,
            ReorderLevel - UnitsInStock AS ShortageAmount
    FROM
        Products) ItemsInStock
WHERE
    ShortageAmount > 0
ORDER BY ShortageAmount DESC;

