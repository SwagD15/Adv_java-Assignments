<html>
<head>
    <title>Shopping Cart Home</title>
</head>
<body>

<h2>Welcome to Shopping Cart</h2>

<form action="cart" method="post">
    <input type="text" name="item" placeholder="Enter item name" required>
    <button type="submit">Add to Cart</button>
</form>

<br>

<form action="cart" method="post">
    <input type="hidden" name="item" value="Laptop">
    <button type="submit">Add Laptop</button>
</form>

<form action="cart" method="post">
    <input type="hidden" name="item" value="Mobile">
    <button type="submit">Add Mobile</button>
</form>

<form action="cart" method="post">
    <input type="hidden" name="item" value="Headphones">
    <button type="submit">Add Headphones</button>
</form>

<br><br>

<a href="cart">View Cart</a>

</body>
</html>