<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JWT Consumed Result</title>
</head>
<body>
<h1>JWT Consumed Result</h1>
<h2>UserId: ${result.getClaim("userId").asString()}</h2>
<h2>OrderId: ${result.getClaim("orderId").asString()}</h2>
</body>
</html>