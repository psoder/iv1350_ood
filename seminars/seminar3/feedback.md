Ludvig H
- No README with instructions for running the program.
- Some bad variable and method names.
- In the Cotnroller endSale and pay should be the same method.
- Doesnt follow the Java naming conventions in all cases (bought_quantity in Item)
- SaleDTO should be merged with Receipt since their functionality is more or less the same.
- isScanned in Sale should be a part of addItem
- Sale could be divided into a Sale and a register.
- totalVAT and totalPrice are unecessary since they can be calculated from the given fields.
- Functionality of updateTotalVat and updateTotalPrice should be moved to Sale.addItem

1. Code conventions are followed overall, some bad names, e.g. eis.
2. Overall the code is easily understood. Some intermediate variables could be useful. Don't use "this" unessarily.
3. No apparent duplicated code.
4. More DTOs could be used, e.g. updateTotalPrice and updateTotalVat. There are some unecessary DTOs 
5. See above.
6. The cohesion, coupling and encapsulation seems fine.
7. Comments should be written on the format,
/**
 * Comment
 *
 * @return some value
 */
 not,
 /**
 * Comment
 * @return some value
 */
A minor difference but it makes the comments more readable.
8. The Layer pattern is used.
9. The tests are very lacking and there's only tests for the View and Main methods but not for the model/controller/integration classes. No tests are actually done.
10. Best practices are used but not very well explained.
