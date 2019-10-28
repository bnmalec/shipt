1. List the stores allowed to sell alcohol.

```
select * from stores where allowed_alcohol = true;
```
	
```
+------+--------+-----------------+
| id   | name   | allowed_alcohol |
+------+--------+-----------------+
|    1 | Gettar |               1 |
+------+--------+-----------------+
1 row in set (0.00 sec)
```




2. Give the product name of the 2 most expensive items based on their price at store id 1.

```
select A.name as "Product Name", round(F.price, 2) as "Price" from store_prices F inner join products A on A.id = F.product_id where F.store_id = 1 order by F.price desc limit 2;
```

```
+---------------+-------+
| Product Name  | Price |
+---------------+-------+
| Golden Banana |  4.00 |
| Banana        |  3.00 |
+---------------+-------+
2 rows in set (0.01 sec)
```

	> Precision/scale is not set for the price in the "interview" schema provided, so the decimals are saved as integers in the database.




3. List the products that are not sold in the store id 2.

``` 
select A.name as "Product Name" from store_prices F right join products A on A.id = F.product_id where A.id not in (select F.product_id from store_prices F where F.store_id = 2);
```

```
+-----------------+
| Product Name    |
+-----------------+
| Banana          |
| Golden Banana   |
| Bouquet Flowers |
+-----------------+
3 rows in set (0.00 sec)
```




4. What is the most popular item sold?

```
select A.name as "Product Name", sum(F.qty) as "Quantity" from order_lines F inner join products A on A.id = F.product_id group by A.id order by F.qty desc;
```

```
+--------------+----------+
| Product Name | Quantity |
+--------------+----------+
| Banana       |       51 |
| Grapes       |        4 |
| Apple        |        3 |
+--------------+----------+
3 rows in set (0.01 sec)
```




5. Write a SQL statement to update the line_total field.
```
select P.price * OL.qty as total from order_lines OL inner join store_prices P on P.store_id = OL.store_id where OL.product_id = P.product_id group by OL.id;
```
 
```
update order_lines set line_total = (select * from(select P.price * OL.qty as total from order_lines OL inner join store_prices P on P.store_id = OL.store_id where OL.product_id = P.product_id group by OL.id) as x);
```
	> The above statements are not quite right - didn't get this one working yet!


