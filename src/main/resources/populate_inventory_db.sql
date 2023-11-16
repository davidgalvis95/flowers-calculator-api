INSERT INTO tblproductpt (id, name, freshCutValue)
SELECT 1, 'Red Roses 23cm', 10
    WHERE NOT EXISTS (SELECT 1 FROM tblproductpt LIMIT 1)
UNION ALL
SELECT 2, 'IL Hydrangea Blue', 15
    WHERE NOT EXISTS (SELECT 1 FROM tblproductpt LIMIT 1)
UNION ALL
SELECT 3, 'Black Gira%%sol 17Inch', 45
    WHERE NOT EXISTS (SELECT 1 FROM tblproductpt LIMIT 1)
UNION ALL
SELECT 4, '&White pom 3Inch', 12
    WHERE NOT EXISTS (SELECT 1 FROM tblproductpt LIMIT 1)
UNION ALL
SELECT 5, 'Achillea$ Blue 23cm', 57
    WHERE NOT EXISTS (SELECT 1 FROM tblproductpt LIMIT 1);


INSERT INTO tblcompanypt (id, name)
SELECT 1, 'BellaFlowers'
    WHERE NOT EXISTS (SELECT 1 FROM tblcompanypt LIMIT 1)
UNION ALL
SELECT 2, 'SuperFlowers'
    WHERE NOT EXISTS (SELECT 1 FROM tblcompanypt LIMIT 1)
UNION ALL
SELECT 3, 'BeautiFlowers'
    WHERE NOT EXISTS (SELECT 1 FROM tblcompanypt LIMIT 1)
UNION ALL
SELECT 4, 'TheFlowers'
    WHERE NOT EXISTS (SELECT 1 FROM tblcompanypt LIMIT 1)
UNION ALL
SELECT 5, 'CandyFlowers'
    WHERE NOT EXISTS (SELECT 1 FROM tblcompanypt LIMIT 1);


INSERT INTO tblboxtypept (id, code, width, height, length)
SELECT 1, '1111', 12.1, 12.8, 11.2
    WHERE NOT EXISTS (SELECT 1 FROM tblboxtypept LIMIT 1)
UNION ALL
SELECT 2, '2222', 11.6, 16.2, 12.7
    WHERE NOT EXISTS (SELECT 1 FROM tblboxtypept LIMIT 1)
UNION ALL
SELECT 3, '3333', 15.2, 18.9, 17.4
    WHERE NOT EXISTS (SELECT 1 FROM tblboxtypept LIMIT 1)
UNION ALL
SELECT 4, '4444', 13.9, 15.5, 10.8
    WHERE NOT EXISTS (SELECT 1 FROM tblboxtypept LIMIT 1)
UNION ALL
SELECT 5, '5555', 14.4, 12.7, 15.5
    WHERE NOT EXISTS (SELECT 1 FROM tblboxtypept LIMIT 1);


INSERT INTO tblinventorypt (id, boxTypeId, productId, companyId, cubesPerCarrier, pack, basePrice)
SELECT 1, 1, 4, 3, 16.3, 1, 1.1
    WHERE NOT EXISTS (SELECT 1 FROM tblinventorypt LIMIT 1)
UNION ALL
SELECT 2, 2, 1, 2, 13.6, 1, 1.7
    WHERE NOT EXISTS (SELECT 1 FROM tblinventorypt LIMIT 1)
UNION ALL
SELECT 3, 3, 5, 5, 11.6, 1, 1.4
    WHERE NOT EXISTS (SELECT 1 FROM tblinventorypt LIMIT 1)
UNION ALL
SELECT 4, 4, 2, 1, 15.2, 1, 1.3
    WHERE NOT EXISTS (SELECT 1 FROM tblinventorypt LIMIT 1)
UNION ALL
SELECT 5, 5, 3, 4, 12.3, 1, 1.2
    WHERE NOT EXISTS (SELECT 1 FROM tblinventorypt LIMIT 1);


INSERT INTO tblcustomerpt (id, name, markdown)
SELECT 1, 'Luis', 5.0
    WHERE NOT EXISTS (SELECT 1 FROM tblcustomerpt LIMIT 1)
UNION ALL
SELECT 2, 'Daniel', 2.0
    WHERE NOT EXISTS (SELECT 1 FROM tblcustomerpt LIMIT 1)
UNION ALL
SELECT 3, 'William', 3.0
    WHERE NOT EXISTS (SELECT 1 FROM tblcustomerpt LIMIT 1)
UNION ALL
SELECT 4, 'Johan', 1.0
    WHERE NOT EXISTS (SELECT 1 FROM tblcustomerpt LIMIT 1)
UNION ALL
SELECT 5, 'Jessica', '4'
    WHERE NOT EXISTS (SELECT 1 FROM tblcustomerpt LIMIT 1);
