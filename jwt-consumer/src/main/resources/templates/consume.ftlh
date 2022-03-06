<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JWT Consume Result</title>
</head>
<body>
<h1>UserId: ${result.getClaim("userId").asString()}</h1>
<h1>OrderId: ${result.getClaim("orderId").asString()}</h1>
</body>
</html>