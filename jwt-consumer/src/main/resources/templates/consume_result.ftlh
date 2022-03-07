<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JWT Consumed Result</title>
</head>
<body>
<h1>JWT Consume Result</h1>
<h2>Issuer: ${result.getIssuer()}</h2>
<h2>Subject: ${result.getSubject()}</h2>
<h2>IssuedAt: ${result.getIssuedAt()?string('dd.MM.yyyy HH:mm:ss')}</h2>
<h2>NotBefore: ${result.getNotBefore()?string('dd.MM.yyyy HH:mm:ss')}</h2>
<h2>ExpiresAt: ${result.getExpiresAt()?string('dd.MM.yyyy HH:mm:ss')}</h2>
<h2>UserId: ${result.getClaim("userId").asString()}</h2>
<h2>OrderId: ${result.getClaim("orderId").asString()}</h2>
<p><a href="http://localhost:9081/jwt/consume/close">Close</a></p>
</body>
</html>