The system is a website for suppliers (providing products) and middlemen (purchasing products). Corresponding to the demand document, suppliers are MVO and middlemen are BVO.

1. MVO

Login Interface

-MVO can register <u>Account</u> in this interface, **after registering **Account and logging in

Company Detail Interface

-Vendors can **edit their own panel information (<u>company</u> information), **add <u>multiple brands</u> that **belong to our company,

Product Management Interface

- **Add **multiple <u>products</u> that belong to one of the brands
-The user can **view **his own product list, select **to put on or off the shelves **goods and store them in the warehouse, where the products on the shelves can be searched **by **middlemen and purchased
- **View, edit **<u>Product Details</u>, which must include **Upload Product Pictures **.

Order Management Interface

-MVO can manage **<u>Orders</u> **, view all their orders, and query order lists in different states: Paid, Paid, Shipped, Completed. Before the product is delivered, the delivery can be cancelled.
-MVO can query the detailed **logistics information of delivered orders **.

Wallet Interface

-You can open your wallet
-Looking at your own consumption flow consists of two parts: one is the increase in money caused by cash withdrawal and the other is the increase in money caused by middlemen paying orders (this is a transaction record).





2. BVO

Login Interface

-Middlemen can register **<u>account</u> on this website to log on **. **After landing **

Personal Information Interface

-Allow middlemen to maintain **their own account information **.
-Add your own eBay or Amazon online store to the platform.
- **View and manage **all of your own <u>online stores</u>.

Browse Product Interface (Core)

-You can use the website **to browse the supplier's products (the function of retrieving products **(multi-condition filtering, etc... filtering brand company information) is implied). You can click **to view the detailed information interface of this product **, and after finding the desired product, you can add the product **to one of your online stores **or **to your <u>wish shopping list **</u>.

Shopping Cart (Wish List) Interface

-View your own list of shopping lists
-Select or delete a product

<u> **Orders **</u> Interface

-Query the **orders to be paid **and select to make payment. In this interface, it is required to be able to view the detailed interface of a certain product.
-Query orders that have not yet been shipped
-Query the orders that have been shipped and view the detailed <u> ***logistics status **</u>
-View cancelled orders (orders not shipped by suppliers)
-Order status is understood as an attribute

Wallet Interface

-You can open your wallet
-Looking at your consumption flow consists of two parts: one is the increase in money caused by top-up and the other is the decrease in money caused by payment orders (transaction records).



Administrator:

-After Administrator **logged on to <u>Account</u> **,

Menu Management Interface

-You can manage <u>menu items</u>, including browsing, querying menu lists, creating, modifying, and deleting menus.

User Management Interface

-You can manage <u>users</u> of the system in a unified way, including browsing, querying user lists, adding, modifying and deleting users.

Financial audit

-The system administrator also needs to review the transaction, which is reflected in the fact that the administrator can view the transaction record flow of saving and withdrawing money (a graphical display of a tabulated interface is required).
-Review the supplier's embodiment behavior and the middleman's recharge behavior (recharge behavior and cash withdrawal can be used as two data stores or as a single table storage) (which can be understood as approval, which can be understood as an act of modifying the recharge and reflecting the status).

