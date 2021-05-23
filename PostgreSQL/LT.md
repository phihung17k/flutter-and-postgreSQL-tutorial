- The column is **field**

- The row is **record**

- ```sql
  /*
      block comment
  */
  --single line comment
  ```

- **Data types**:
  
  - Text: string
    
    - TEXT
    
    - CHAR
    
    - VARCHAR
  
  - Numeric: quantities and measurements
    
    - SMALLINT
    
    - INTEGER
    
    - SERIAL
    
    - FLOAT(n)
    
    - REAL and FLOAT8
    
    - NUMBERIC or NUMBERIC(p, s)
    
    - DECIMAL
  
  - Temporal: dates and times
    
    - : date
    
    - TIME: time of day
    
    - TIMESTAMP: date and time
    
    - TIMESTAMPTZ: date and time with timezone
    
    - INTERVAL: periods of time
  
  - Boolean: true and false
    
    - BOOLEAN or BOOL: true(default), false or null

- **Create table**: 
  
  - ```sql
    CREATE TABLE table_name(
        col_name datatype constraint,
        col_name datatype constraint
    );
    ```

- **Insert table**
  
  - For 1 row
    
    - ```sql
      INSERT INTO table_name(col1, col2, ...)
      VALUES (value1, value2, ...);
      ```
  
  - For many rows
    
    - ```sql
      INSERT INTO table_name(col1, col2, ...)
      VALUES (value1, value2, ...),
             (value1, value2, ...);
      ```
  
  - For another table
    
    - ```sql
      INSERT INTO table_name(col1, col2, ...)
      SELECT [DISTINCT] col1, col2, ...
      FROM another_table
      WHERE condition;
      ```

- **Add column**
  
  - For 1 column
    
    - ```sql
      ALTER TABLE table_name 
      ADD COLUMN new_column_name data_type DEFAULT value;
      ```
  
  - For many columns
    
    - ```sql
      ALTER TABLE table_name
      ADD COLUMN column_name_1 data_type constraint DEFAULT value,
      ADD COLUMN column_name_2 data_type constraint DEFAULT value;
      ```

- **Change column type**
  
  - For 1 column
    
    - ```sql
      ALTER TABLE table_name 
      ALTER COLUMN column_name [SET DATA] TYPE new_data_type;
      ```
  
  - For many column
    
    - ```sql
      ALTER TABLE table_name
      ALTER COLUMN column_name_1 [SET DATA] TYPE new_data_type,
      ALTER COLUMN column_name_2 [SET DATA] TYPE new_data_type;
      ```

- **Rename column**
  
  - ```sql
    ALTER TABLE table_name RENAME COLUMN col_name TO new_col_name;
    or //COLUMN in RENAME COLUMN is optional
    ALTER TABLE table_name RENAME col_name TO new_col_name;
    ```

- **Constraint**: The predefined model where you enforce(gọi) data types, relationships and other rules. These rules are call integrity constraints. Integrity constraints can be divided into 3 types:
  
  - Attribute constraint: data types on column
  
  - Key constraint: primary key
  
  - Referential(tham chiếu) integrity(toàn vẹn) constraints: enforced through foreign keys
  
  - **Add Constraint**
    
    - ```sql
      ALTER TABLE table_name
      ADD CONSTRAINT contraint_name constraint_definition;
      ```
  
  - 2 special atrribute constraints: `NOT NULL` (default NULL)and `UNIQUE`
  
  - **Add Primary key**
    
    - For a not-exist table
      
      - ```sql
        CREATE TABLE (
            col_1 datatype PRIMARY KEY,
            col_2 datatype
        );
        or
        CREATE TABLE (
            col_1 datatype,
            col_2 datatype
            PRIMARY KEY (col_1, col_2)
        );
        ```
    
    - For a existed table
      
      - ```sql
        ALTER TABLE table_name
        ADD CONSTRAINT some_name PRIMARY KEY(column_name)
        ```
  
  - **Foreign key**s: a column point to a primary key of another table
    
    - Add Foreign key
      
      - ```sql
        CREATE TABLE table_name(
            col1 datatype PRIMARY KEY,
            col2 datatype REFERENCES another_table(primary_key)
        );
        or
        CREATE TABLE table_name(
            col1 datatype PRIMARY KEY,
            col2 datatype,
            FOREIGN KEY (col2) REFERENCES another_table(primaryKey)
        );
        or
        ALTER TABLE a
        ADD CONSTRAINT a_fkey FOREIGN KEY(b_id) REFERENCES b(id);
        ```
  
  - **Referential integrity**
    
    - Principle:
      
      - A references B. If a record in B that is referenced from A is deleted, violation.
      
      - Refer to a recored that is not existed, violation
    
    - To avoid violate the principle Referential integrity, use `ON DELETE ON ACTION` for foreign key
    
    - ```sql
      CREATE TABLE a(
          id INTEGER PRIMARY KEY,
          col ...,
          b_id INTEGER REFERENCES b(id) ON DELETE NO ACTION
      );
      ```
    
    - `ON DELETE NO ACTION | ON DELETE CASCADE`

- `CHECK` constraint 
  
  - ```sql
    CREATE TABLE a(
        id ...,
        birth_date DATE CHECK (birth_date > '2000-01-01')
    );
    or
    ALTER TABLE a
    ADD CONSTRAINT birth_date CHECK (birth_date > '2000-01-01')
    ```

- `DROP` constraint: delete `UNIQUE, PRIMARY KEY, FOREIGN KEY, CHECK` constraint
  
  - ```sql
    ALTER TABLE table_name DROP CONSTRAINT constraint_name;
    or
    ALTER TABLE table_name 
    DROP CONSTRAINT constraint_name1,
    DROP CONSTRAINT constraint_name2;
    ```

- **Update value of column**
  
  - ```sql
    UPDATE table
    SET column1 = value1, column2 = value2 ,...
    WHERE condition;      //ex: col1 IS NULL  
    ```

- **Update all column**
  
  - ```sql
    UPDATE table
    SET column = value;
    ```

- **Update the columns base on the columns of another table**
  
  - ```sql
    UPDATE table_a
    SET column_to_update = table_b.column_to_update_from
    FROM table_b
    WHERE condition1 AND condition2 OR ...;
    ```

- **Drop colum**
  
  - ```sql
    ALTER TABLE table_name 
    DROP COLUMN column_name;
    ```
  
  - If the column has its dependencies such as triggers, stored procedure, ... . You must use `CASCADE` to drop the column and its dependencies
  
  - ```sql
    ALTER TABLE table_name 
    DROP COLUMN column_name CASCADE;
    ```
  
  - To avoid the deleted column is not exist, using `IF EXISTS`
  
  - ```sql
    ALTER TABLE table_name 
    DROP COLUMN IF EXISTS column_name;
    ```
  
  - Drop many columns
    
    ```sql
    ALTER TABLE table_name
    DROP COLUMN column_name_1,
    DROP COLUMN column_name_2;
    ```

- **Drop table**
  
  - ```sql
    DROP TABLE [IF EXISTS] table_name [CASCADE | RESTRICT];
    ```
    
    - CASCADE: remove table and its dependency
    
    - RESTRICT: default, refuse to drop table if it has dependency

- **Delete data from table**
  
  - ```sql
    DELETE FROM table 
    WHERE condition;
    ```
  
  - example: delete the rows that have `id` is not 1 and 5
    
    ```sql
    DELETE FROM link 
    WHERE id NOT IN (1, 5);
    ```

- **Truncate table**
  
  - Remove data from one or more table. It has the same effect as DELETE but faster (don't scan table) and reclaim(giải phóng) disk space immediately
  
  - ```sql
    TRUNCATE TABLE table_name;
    or
    TRUNCATE TABLE table_name1, table_name2;
    or
    TRUNCATE TABLE table_name [ RESTART IDENTITY | CONTINUE IDENTITY ]
                              [ CASCADE | RESTRICT ]
    ```
  
  - By default, `TRUNCATE` doesn't remove data from the table that has foreign key references. If want, using `CASCADE`

- **Data normalization(chuẩn hóa):**
  
  - Protect from data anomalies(kì dị) and ensure the integrity of data
  
  - If not data normalization, data is redundancy, eat up extra memory space, difficult to handle database
  
  - Benefits:
    
    - Data duplication is reduced
    
    - Data consistency is increased so that entites don't have conflicting data in different tables
    
    - Data is organized so that data objects better map to table records

- **1 Normal Form (1NF):**
  
  - Table value must be atomic meaning the value cannot be divided in to smaller units

- **2 Normal Form (2NF)**
  
  - Satifies 1NF 
  
  - Non-key columns only depend on the table's PRIMARY KEY

- **3 Normal Form (3NF)**
  
  - Satify 2NF
  
  - No transitive dependencies are present in the table

- **Select table**
  
  - ```sql
    SELECT column_1, column_2, ... 
    FROM table_name;
    ```

- **Alias**
  
  - Column alias
    
    - ```sql
      SELECT column_name AS alias_name FROM table;
      or
      SELECT column_name alias_name FROM table;
      ```
    
    - `AS` keyword is optional, can skip it
    
    - Example, find full_name, `||''||` to combine 2 strings
      
      ```sql
      SELECT first_name || ' ' || last_name AS full_name FROM actor
      ```
      
      'abc' ||' '|| 'de'   ==> 'abc de'
  
  - Table alias
    
    - ```sql
      SELECT column_list FROM table_name AS alias_name;
      or
      SELECT column_list FROM table_name alias_name;
      ```
    
    - Example, `t` is alias
      
      ```sql
      SELECT t.column_name FROM a_very_long_table_name t;
      ```
  
  - Select alias
    
    ```sql
    SELECT 5 * 3 AS result;
    ```

- **Convert data type into another**
  
  - `CAST` keyword
    
    ```sql
    CAST( expression AS target_type );
    ```
  
  - `::` is used to convert
    
    ```sql
    expression::type
    ```
  
  - Example
    
    ```sql
    SELECT CAST('100' AS INTEGER); --100
    SELECT CAST('01-OCT-2015' AS DATE); --2015-10-01
    SELECT CAST('10.2' AS DOUBLE PRECISION); --10.2
    SELECT CAST('true' AS BOOLEAN); --true
    SELECT '15 minute'::INTERVAL; --00:15:00
    SELECT '2 week'::INTERVAL; --14 days
    ```

- `ROUND()` (làm tròn)
  
  - Round a numberic value
  
  - ```sql
    ROUND (source [ , n ] )
    ```
    
    - `source`: the number which is rounded
    
    - `n` in `, n`: the number of decimal after rounded. It is optional. If n is not specified, default = 0. 
  
  - Example, the number of decimal <5 => round down. If >= 5  => round up
    
    ```sql
    SELECT ROUND(10.4); --Kết quả trả về là 10 
    SELECT ROUND(10.5); --Kết quả trả về là: 11 
    SELECT ROUND(10.812, 2); --Kết quả trả về là: 10.81
    ```

- `ORDER BY` statement
  
  - Allow to sort the rows that appear in `SELECT` for ascending (default) or descending
    
    ```sql
    SELECT column_1, column_2
    FROM tbl_name
    ORDER BY column_1 ASC, column_2 DESC;
    ```

- `SELECT DISTINCT` 
  
  - Remove duplicate rows, DISTINCT clause keeps one row for each group
  
  - ```sql
    SELECT DISTINCT column_1 FROM table_name;
    or // col_1 
    SELECT DISTINCT column_1, column_2 FROM table_name;
    ```
  
  - Note: the rows in `DISTINCT ON` must match the rows in `ORDER BY` . Example
    
    ```sql
    SELECT DISTINCT bcolor 
    FROM color
    ORDER BY bcolor;
    or
    SELECT DISTINCT bcolor, fcolor 
    FROM color
    ORDER BY bcolor, fcolor;
    ```

- `WHERE` clause
  
  - Order to operate: FROM -> WHERE -> SELECT
  
  - Use condition to filter the rows returned from the `SELECT` . The condition must evaluate true, false and unknown
    
    ```sql
    SELECT select_list
    FROM table_name
    WHERE condition;
    ```
  
  - The standard comparison operators
    
    | Operator | Description           |
    | -------- | --------------------- |
    | =        | Equal                 |
    | >        | Greater than          |
    | <        | Less than             |
    | >=       | Greater than or equal |
    | <=       | Less than or equal    |
    | <> or != | Not equal             |
    | AND      | Logical operator AND  |
    | OR       | Logical operator OR   |
  
  - `IN` operator
    
    - To check match a string with any string in a list
      
      ```sql
      SELECT first_name, last_name
      FROM actor
      WHERE first_name IN ('PENELOPE','NICK');
      ```
  
  - `LIKE` operator
    
    - `LIKE` and a string contains
      
      - Percent `%`: match any sequence of characters (many)
      
      - Underscore `_` : match any single character (1) 
    
    - `NOT LIKE` and `LIKE`
      
      ```sql
      SELECT first_name, last_name
      FROM actor
      WHERE first_name LIKE 'JEN%';
      ```
  
  - `ILIKE` operator
    
    - `ILIKE` act like `LIKE` operator. In addition, `ILIKE` matchs case-insensitive (không phân biệt hoa thường)
    
    - `~~` = `LIKE`
    
    - `~~*` = `ILIKE`
    
    - `!~~` = `NOT LIKE`
    
    - `!~~*` = `NOT ILIKE`

- `BETWEEN` operator
  
  - Match a range of values
  
  - ```sql
    value BETWEEN low AND high;
    value NOT BETWEEN low AND high;
    ```
  
  - Example 
    
    ```sql
    SELECT full_name
    FROM payment
    WHERE amount BETWEEN 8 AND 9;
    ```

- `LIMIT` clause
  
  - Limit the rows returned by query
    
    ```sql
    SELECT * 
    FROM table_name LIMIT n;
    ```
  
  - `n` rows generated. If n=0, empty set. If n=NULL, query withour `LIMIT`
  
  - Because the order of the rows is unspecified, use `ORDER BY` is necessary, ex
    
    ```sql
    SELECT film_id, title, release_year
    FROM film
    ORDER BY film_id
    LIMIT 3;
    ```

- `OFFSET` clause
  
  - Skip a number of rows before return the n rows, use `OFFSET` after `LIMIT`. The order operate: `OFFSET` -> `LIMIT`
    
    ```sql
    SELECT * FROM table 
    LIMIT n OFFSET m;
    ```
    
    - Skip `m` rows before return `n` rows generated. If m=0, without `OFFSET`

- `NULL` operator
  
  - Use `IS NULL` or `IS NOT NULL` in `WHERE`
    
    ```sql
    value IS NULL
    value IS NOT NULL
    ```
  
  - Example:
    
    ```sql
    SELECT id, first_name, last_name, email, phone
    FROM contacts
    WHERE phone IS NULL;
    ```

- `INNER JOIN` clause
  
  - A has primary key which is foreign key in B
    
    ```sql
    SELECT ...
    FROM table_A a INNER JOIN table_B b ON a.pk = b.fk;
    ```

- `COALESCE` clause
  
  - Return the first number which is not null. If all number are null, return null. Accept an unlimited arguments
    
    ```sql
    COALESCE (argument_1, argument_2, …);
    ```
  
  - Ex, if sector in row is not null, industry = sector. If sector is null, industry is null
    
    ```sql
    SELECT rank, title, name, 
           COALESCE(sector, 'Unknown') AS sector,
           COALESCE(industry, sector, 'Unknown') AS industry, 
           employees
    FROM fortune;
    ```

- `LEFT JOIN` or `LEFT OUTER JOIN`
  
  - Primary key of A is refer to foreign key of B
  
  - Return all rows in the left table A that combine with rows in the right table B. If no corresponding rows in B, return null
    
    ```sql
    SELECT ...
    FROM A LEFT JOIN B ON A.pk = B.fk
    ```
  
  - Example:
    
    - Table A
      
      | film_id | title | length |
      | ------- | ----- | ------ |
      | 1       | a     | 90     |
      | 2       | b     | 80     |
      | 3       | c     | 70     |
    
    - Table B
      
      | publish_id | location | film_id |
      | ---------- | -------- | ------- |
      | 1          | m        | 1       |
      | 2          | m        | 1       |
      | 3          | m        | 1       |
    
    - ```sql
      SELECT a.film_id, a.title, a.length, b.publish_id
      FROM A a LEFT JOIN B b ON a.film_id = b.film_id
      ```
    
    - Result
      
      | film_id | title | length | publish_id |
      | ------- | ----- | ------ | ---------- |
      | 1       | a     | 90     | 1          |
      | 1       | a     | 90     | 2          |
      | 1       | a     | 90     | 3          |
      | 2       | b     | 80     | null       |
      | 3       | c     | 70     | null       |

- Self-join 
  
  - Use join to itself. Can be use `INNER JOIN`, `LEFT JOIN` or `RIGHT JOIN`
    
    ```sql
    SELECT column_list 
    FROM A a1 INNER JOIN A b1 ON join_predicate;
    ```
  
  - Example:
    
    - Table `staffs`
      
      | staff_id | first_name | last_name | manager_id |
      | -------- | ---------- | --------- | ---------- |
      | 1        | Fa         | Ja        | NULL       |
      | 2        | Mi         | Co        | 1          |
      | 3        | Ge         | Se        | 2          |
    
    - ```sql
      SELECT e.first_name ||' '|| e.last_name employee,
             m.first_name ||' '|| m.last_name manager
      FROM staffs e INNER JOIN staffs m ON m.staff_id = e.manager_id
      ORDER BY manager;
      ```
    
    - Result
      
      | staff_name | manager_name |
      | ---------- | ------------ |
      | Mi Co      | Fa Ja        |
      | Ge Se      | Mi Co        |

- **Sum** function
  
  - Sum of values or distinct value
    
    ```sql
    SUM(DISTINCT column) 
    ```

- **MIN, MAX, AVG** function
  
  - ```sql
    SELECT MIN(column) FROM table;
    SELECT MAX(column) FROM table;
    SELECT AVG(column) FROM table;
    ```

- **GROUP BY** clause
  
  - Divides the rows returned  `SELECT` into groups. Nhóm các row lại
  
  - Cantain all rows in `SELECT` except SUM, MIN, ...
  
  - Can be apply aggregate(tổng hợp) function: `SUM()` to calculate sum or `COUNT()`to get the number of items in the groups
  
  - ```sql
    SELECT column_1, aggregate_function(column_2)
    FROM tbl_nam
    WHERE ...
    GROUP BY column_1;
    ORDER BY ...
    ```

- **COUNT** function
  
  - ```sql
    SELECT id, COUNT(id) count
    FROM ...
    GROUP BY id;
    ```

- **HAVING** clause
  
  - Conjunction (kết hợp) with `GROUP BY` to filter group rows that do not satify a specified condition
  
  - ```sql
    SELECT column_1, aggregate_function(column_2)
    FROM tbl_name
    GROUP BY column_1
    HAVING condition;
    ```
  
  - `HAVING` set condition for group rows created by `GROUP BY`  **after** `GROUP BY` applied. Whille `WHERE` set condition for individual rows **before** `GROUP BY` applied
  
  - `HAVING` can be used withour `GROUP BY`, it return a single group
  
  - `HAVING` can only refer to columns from within aggregate functions in `SELECT`
  
  - Return a single row if condition `HAVING` is true. 0 row if it is false

- **Subquery**
  
  - Ex: find films with rental rate > avg (rental rate)
  
  - ```sql
    SELECT film_id, title, rental_rate
    FROM film
    WHERE rental_rate>( SELECT AVG (rental_rate)
                        FROM film);
    ```

- **EXISTS**
  
  - ```sql
    EXISTS (subquery)
    ```
    
    - return true if at least 1 or false if no row

- **FLOOR**
  
  - Round down

- **Absolute**
  
  - ABS

- DIV
  
  - Lấy phần nguyên

- CHAR_LENGTH('string')
  
  - Determine the number of characteres in a string

- LEFT('string', n)
  
  - get n left characters in string

- RIGHT('string', n)
  
  - get n right character in string

- SUBSTRING(string, start_position, length)
  
  - ```sql
    SUBSTRING(string, start_position, length)
    or
    SUBSTRING (string from start_position for length);
    //ex
                       12345678910
    SELECT SUBSTRING ('PostgreSQL', 1, 8); -- PostgreS
    SELECT SUBSTRING ('PostgreSQL', 8); -- SQL
    SELECT SUBSTRING ('PostgreSQL' FROM 1 FOR 8); -- PostgreS
    SELECT SUBSTRING ('PostgreSQL' FROM 8); -- SQL
    ```
  
  - start_position: specific where extract(trích xuất) substring. If start =0, first char
  
  - length: number of characters that extract from string

- POSITION
  
  - Return position of character in string from left to right
  
  - ```sql
    POSITION('@' IN email)
    ```

- Concatenate strings
  
  - ```sql
    SELECT 'Concatenation' || ' ' || 'Operator'; 
    -- The result is: Concatenation Operator
    ```

```sql
CONCAT(str_1, str_2, ...)
SELECT CONCAT('CONCAT',' ', 'function'); 
-- The result is: CONCAT function
```

- UPPER(string)

- LOWER(string)

- INITCAP(string): Chữ đầu tiên viết hoa

- REVERSE(string): đảo string

- REPLACE(string, substring in string, replace string)

- LPAD(string, length [, fill])
  
  - string: origin string
  
  - length: length of string after padding(đệm thêm)
  
  - fill: optional, default space, string is used for padding from left
  
  - ```sql
    SELECT LPAD('PostgreSQL',15,'*');
    ==> RS:*****PostgreSQL
    ```

- TRIM
  
  - ```sql
    TRIM([LEADING | TRAILING | BOTH] [characters] FROM string)
    TRIM(string)
    SELECT 
       TRIM(LEADING FROM '  PostgreSQL TRIM'), --The result: 'PostgreSQL TRIM'
       TRIM(TRAILING FROM 'PostgreSQL TRIM   ' ), --The result: 'PostgreSQL TRIM'
       TRIM('  PostgreSQL TRIM  '); --The result: 'PostgreSQL TRIM'
    ```
  
  - `LTRIM, RTRIM, BTRIM` are short version of `TRIM`
  
  - Remove whitespace between letters using REGEXP_REPLACE()
    
    ```sql
    The result is: 'this is code learn'
    SELECT REGEXP_REPLACE('this  is code     learn', '\s+', ' ', 'g');
    ```
    
    - `\s`: whitespace
    
    - `+`: 1 or more matches
    
    - `g`: replace all substring instead of first substring

**DATE/TIME**

- `NOW` retrieve a timestamp value for current date and time with timezone
  
  ```sql
  SELECT NOW(); --2021-05-12 03:31:18.44862+07
  SELECT NOW()::TIMESTAMP; --2021-05-12 03:31:18.44862
  ```

- `CURRENT_TIMESTAMP`: return the same result with `NOW`. `CURRENT_TIMESTAMP(n)` can round fractional digits(số thập phân đến số thứ `n`)

- ```sql
  SELECT CURRENT_DATE; --2021-05-12
  SELECT CURRENT_TIME; --03:31:18.44862+07
  ```

- `EXTRACT` function ::integer
  
  - Retrieve a field such as a year, a month, a day from a date/time value
    
    ```sql
    EXTRACT(field FROM source)
    ```
  
  - Field list
    
    | Field  | Timestamp                              |
    | ------ | -------------------------------------- |
    | DAY    | day of month (1-31)                    |
    | DOW    | day of week Sunday (0) to Saturday (6) |
    | DOY    | day of year (1-366)                    |
    | HOUR   | hour of day (0-23)                     |
    | MINUTE | minute (0-59)                          |
    | MONTH  | Month, 1-12                            |
    | WEEK   | week of year (ISO 8601)                |
    | YEAR   | year                                   |
  
  - Source: `TIMESTAMP` or `INTERVAL`. If you pass `DATE`, it will cast to `TIMESTAMP`
  
  - ex
    
    ```sql
    SELECT EXTRACT(YEAR FROM TIMESTAMP '2016-12-31 13:30:15');   
    --The result is: 2016
    SELECT EXTRACT(MONTH FROM TIMESTAMP '2016-12-31 13:30:15'); 
     --The result is: 12
    SELECT EXTRACT(DOW FROM TIMESTAMP '2016-12-31 13:30:15')
    ; -- The result is: 6
    SELECT EXTRACT(DOY FROM TIMESTAMP '2016-12-31 13:30:15'
    ); -- The result is: 366
    SELECT EXTRACT(MONTH FROM INTERVAL '6 years 5 months 4 days'); 
    --The result is: 5
    ```

- `AGE`::text
  
  - Calculate 2  `TIMESTAMP`
    
    ```sql
    AGE(TIMESTAMP,TIMESTAMP);
    ```
  
  - ex:
    
    ```sql
    SELECT AGE('2017-01-01','2011-06-24'); -- 5 years 6 mons 7 days
    ```

- `INTERVAL`
  
  - Store a period of time such as year, month, day, hour, ..., use for arithmetic
    
    ```sql
    @ INTERVAL [field] [(p)]
    INTERVAL '2 months ago';
    INTERVAL '3 hours 20 minutes';
    ```
  
  - Store size 16 bytes
  
  - `p` is number of fraction digits 
  
  - `@` is optional
  
  - ex: calculate time in past from present, 1 year 3 hours 20 minutes
    
    ```sql
    SELECT
       now(), now() - INTERVAL '1 year 3 hours 20 minutes' as pass;
    -- now: 2017-02-20 20:01:54.0404-08    
    -- past: 2016-02-20 16:41:54.0404-08
    SELECT INTERVAL '2h 50m' + INTERVAL '10m'; -- 03:00:00 
    SELECT INTERVAL '2h 50m' - INTERVAL '50m'; -- 02:00:00 
    SELECT 600 * INTERVAL '1 minute'; -- 10:00:00
    ```
  
  - NOTE: `TIMESTAMP` value convert to `TEXT` 
    
    ```sql
    return_date::text AS return_date
    //result: 
    //  2005-05-24 22:54:00.0    TIMESTAMP
    //  2005-05-24 22:54:00        TEXT
    ```

- `TO_DATE`
  
  - ```sql
    TO_DATE(text,format)
    ```
  
  - text: string which want to convert to a date
  
  - format: format to convert
    
    ```sql
    SELECT TO_DATE('20170103', 'YYYYMMDD')
    -- 2017-01-03
    ```

- `DATE_PART()`
  
  - extracts a subfield from a date or time value
  
  - ```sql
    DATE_PART(field,source)
    SELECT DATE_PART('year',TIMESTAMP '2017-01-01'); --2017
    SELECT DATE_PART('hour',TIMESTAMP '2017-03-18 10:20:30'); --10
    SELECT DATE_PART('minute',TIMESTAMP '2017-03-18 10:20:30'); --20
    SELECT DATE_PART('second',TIMESTAMP '2017-03-18 10:20:30');
    ```
  
  - field: 'year', 'month', 'hour', ...
  
  - source: `TIMESTAMP`, `TIME`, `INTERVAL`. If is `DATE`, auto convert to`TIMESTAMP`

- `DATE_TRUNC`
  
  - ```sql
    DATE_TRUNC(field, source)
    SELECT DATE_TRUNC('hour',TIMESTAMP '2017-03-18 10:20:30');
    --2017-03-18 10:00:00
    SELECT DATE_TRUNC('minute',TIMESTAMP '2017-03-18 10:20:30');
    --2017-03-18 10:20:00
    ```
  
  - Làm a `TIMESTAMP` or an `INTERVAL` tròn xuống

**SET** 

- Dùng trong `FROM() AS alias_name` 

- Union (lấy hết, không trùng)
  
  - Combine sets of two or more `SELECT`, NOT duplicate
    
    ```sql
    SELECT column_1, column_2 FROM tbl_name_1 
    UNION 
    SELECT column_1, column_2 FROM tbl_name_2;
    ```
    
    - queries must return the same number of columns
    
    - Corresponding columns have compatible data types

- Union all (lấy hết, cả trùng)
  
  - the same `UNION` but allow duplicat

- INSPECT (lấy phần chung)
  
  - combines the result sets of two or more SELECT statements into a single result set
  
  - data only appear in both set of tables
  
  - ```sql
    SELECT col_list FROM A
    INTERSECT 
    SELECT col_list FROM B
    ```
    
    - Number of columns and their order in `SELECT` must be the same
    
    - The data type of columns must be compatible

- EXCEPT 
  
  - returns distinct rows from the first (left) query that are not in the output of the second (right) query
  
  - ```sql
    SELECT column_list FROM A WHERE condition_a
    EXCEPT 
    SELECT column_list FROM B WHERE condition_b;
    ```
    
    - The number of columns and their orders must be the same in the two queries.
    
    - The data types of the respective columns must be compatible.
  
  - `ORDER BY` clause at the end of the statement
  
  - the `EXCEPT` operator is applied `ORDER BY` to the both queries

**Conditional expression**

- `CASE ... WHEN ... END`
  
  - ```sql
    CASE 
          WHEN condition_1  THEN result_1
          WHEN condition_2  THEN result_2
          [WHEN ...]
          [ELSE result_n]
    END
    or
    CASE expression
          WHEN value_1 THEN result_1
          WHEN value_2 THEN result_2
          [WHEN ...]
          [ELSE result_n]
    END;
    or
    select (case ... when... end) as alias_name //phải có as
    ```
  
  - Each condition return a boolean value : true orr false
  
  - If condition value is true, `CASE` return the result corresponding, other `CASE` don't process
  
  - If all `CASE` are false, return result in `ELSE`. If omit `ELSE`, return null

- `SUM`

**Supquery**

- `WHERE` chỉ thực thi 1 lần cho toàn bộ câu lệnh FROM->WHERE->SELECT

**Common table expression**

- CTE: return a temporary result table/set which refer within another SQL statement  like SIUD
  
  - ```sql
    WITH cte_name (column_list) AS (
        CTE_query_definition
    ),
    cte_name (column_list) AS (
        CTE_query_definition
    ),
    statement;
    ```
    
    - If column_list is not specified, the `SELECT` list of `CTE_query_definition` will become column_list
  
  - CTE run only once, stored in memory
  
  - Delare many CTE if need, one after another
  
  - You have 3 CTEs, the third CTE can retrieve the first and second one
  
  - CTE can reference itself by recursive

- CTE recursive
  
  - ```sql
    WITH RECURSIVE cte_name AS(
        CTE_query_definition -- non recursive
        UNION [ALL]
        CTE_query definition  -- recursive term
    ) SELECT * FROM cte_name;
    or
    WITH RECURSIVE subordinates AS (
        SELECT employee_id, manager_id, fullname
        FROM employees
        where employee_id=2
        UNION
        SELECT e.employee_id, e.manager_id, e.fullname
        FROM employees e
        INNER JOIN subordinates s ON s.employee_id=e.manager_id
    )SELECT * FROM subordinates;
    ```

**Window function**

- ```sql
  window_func(arg1, arg2,...) OVER(
      [PARTITION BY partition_expression]
      [ORDER BY sort_expression [ASC | DESC] [NULLS {FIRST | LAST}]]
  )
  ```

- window_func: name of function. Some window function don't accept any argument

- ORDER BY and PARTITION BY are two most common subclauses used
  
  - PARTITION BY: 
    
    - Optional, divide rows into multiple groups
    
    - If skip it, window will treat whole result as a single partition
  
  - ORDER BY:
    
    - Specify the order of rows in each partition
    - `NULL FIRST` value: is value allow the null value in the first of result set
    - `NULL LAST` value: the similar FIRST, default

- `ROW_NUMBER` 
  
  - Is a window function to assign a sequential integer to each row in a result set
  
  - ```sql
    ROW_NUMBER() OVER(
        [PARTITION BY col_1, col_2, ...]
        [ORDER BY col_3, col_4,...]
    )
    ```
  
  - `PARTITION BY`: divide window into smaller set. Default, start =1 for the first row of each small set
  
  - ```sql
    SELECT 
        ROW_NUMBER() OVER() AS row_number,
        product_id, product_name,
    FROM products;
    ```
  
  - Example:
  
  - | id  |
    | --- |
    | a   |
    | b   |
    | c   |
  
  - Result 
    
    | row_number | id  |
    | ---------- | --- |
    | 1          | a   |
    | 2          | b   |
    | 3          | c   |

- `LAG` function
  
  - provide access to a row that come before the current row
  
  - the current row, `LAG` func can access data of the previous row or previous more. Use to compare value of current and previous row
    
    ```sql
    LAG(expression [, offset [, default_value]])
    OVER(
        [PARTITION BY partition_expression, ...]
    ORDER BY sort_expression [ASC | DESC], ...
    )
    ```
  
  - Ex: 
  
  - ```sql
    WITH cte AS (
        SELECT year, SUM(amount) amount
        FROM sales
        GROUP BY year
        ORDER BY year
    )
    SELECT year, amount, 
           LAG(amount, 1) OVER (ORDER BY year) previous_year_salse
    FROM cte
    ```
  
  - | year | amount |
    | ---- | ------ |
    | 2018 | 5      |
    | 2018 | 6      |
    | 2019 | 3      |
  
  - result
  
  - | year | amount | previous_year_sales |
    | ---- | ------ | ------------------- |
    | 2018 | 11     | null                |
    | 2019 | 3      | 11                  |

- `LEAD` provide access to a row that follows the current row
  
  - From current row, `LEAD` can access data of the next row, ... and more
  
  - ```sql
    LEAD(expression [, offset [, default_value]])
    OVER(
        [PARTITION BY partition_expression, ...]
        ORDER BY sort_expression [ASC | DESC], ...
    )
    ```
  
  - expression: a column, expression, subquery must be evaluated into a unique value, not a window function
  
  - offset: the `n` th row (lấy data từ hàng thứ n), default 1
  
  - default_value: default NULL if beyond the scope  

- `FIRST_VALUE`
  
  - return a value evaluated against the first row in a sorted partition of a result set
  
  - ```sql
    FIRST_VALUE(expression)
    OVER(
        [PARTITION BY partition_expression, ...]
        ORDER BY sort_expression [ASC | DESC], ...
    )
    ```
  
  - ex
  
  - | name | price |
    | ---- | ----- |
    | a    | 400   |
    | b    | 100   |
    | c    | 200   |
  
  - ```sql
    select name, price,
            FIRST_VALUE(name) over(order by price) lowest_price
    FROM products;
    ```
  
  - result
    
    | name | price | lowest_price |
    | ---- | ----- | ------------ |
    | a    | 400   | b            |
    | b    | 100   | b            |
    | c    | 200   | b            |

- `RANK`
  
  - assign a rank to every row within a partition of result set. First rank is 1.
  
  - Rank can the same value, may not be sequential
  
  - ```sql
    RANK() OVER(
        [PARTITION BY partition_expression, ...]
        ORDER BY sort_expression [ASC | DESC], ...
    )
    ```
  
  - ex
  
  - | c   |
    | --- |
    | A   |
    | A   |
    | B   |
  
  - ```sql
    select c, RANK() over(order by c) rank_num
    FROM ranks;
    ```
  
  - result: 2 A rank 1, so B rank 3
    
    | c   | rank_num |
    | --- | -------- |
    | A   | 1        |
    | A   | 1        |
    | B   | 3        |

- `DENSE_RANK`
  
  - assigns a rank to every row in each partition of a result set. Different from the `RANK` function, the `DENSE_RANK` function always returns consecutive rank values. (trả giá trị liên tiếp)
  
  - Rank can the same value, may not be sequential
  
  - ```sql
    DENSE_RANK() OVER(
        [PARTITION BY partition_expression, ...]
        ORDER BY sort_expression [ASC | DESC], ...
    )
    ```
  
  - ex
  
  - ![](E:\OJT\PostgreSQL\img\dense_rank.jpg)
  
  - ```sql
    SELECT
      product_id, product_name, group_id, price,
      DENSE_RANK() OVER (
        PARTITION BY group_id
        ORDER BY price DESC
      ) price_rank
    FROM products;
    ```
  
  - result: 2 A rank 1, so B rank 3
    
    ![](E:\OJT\PostgreSQL\img\output_dense.jpg)

- `NTILE`
  
  - <img src="file:///E:/OJT/PostgreSQL/img/ntile.jpg" title="" alt="" width="472">
  
  - <img src="file:///E:/OJT/PostgreSQL/img/ntile2.jpg" title="" alt="" width="403">
  
  - ![](E:\OJT\PostgreSQL\img\ntile3.jpg)
