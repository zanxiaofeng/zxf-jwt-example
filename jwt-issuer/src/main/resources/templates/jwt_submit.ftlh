<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JWT Token Auto Submit</title>
</head>
<body>
<h1>UserId: ${userId}</h1>
<h1>OrderId: ${orderId}</h1>
<form action="${targetUrl}" method="POST" target="_blank">
    <input type="hidden" name="jwt" value="${jwt}">
    <input type="submit">
</form>
</body>
</html>