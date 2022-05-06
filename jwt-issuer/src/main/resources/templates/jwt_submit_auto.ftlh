<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Submit This Form</title>
    </head>
    <body onload="javascript:document.forms[0].submit()">
        <form action="http://localhost:9081/jwt/consume/gateway" method="POST" target="_blank">
            <input type="hidden" name="jwt" value="${jwt}">
        </form>
    </body>
</html>