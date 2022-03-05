<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JWT Auto Submitter</title>
</head>
<body>
<h1>${businessId}</h1>
<form action="${targetUrl}" method="POST" target="_blank">
    <input type="hidden" name="jwt" value="${jwt}">
    <input type="submit">
</form>
</body>
</html>