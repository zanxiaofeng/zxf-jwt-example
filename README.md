# JWT Algorithm
| JWT Algorithm                          |
|----------------------------------------|
| RS256(RSASSA-PKCS1-v1_5 using SHA-256) |

# JWT Payloads
## Standard payloads
| Field |        Value Example        |          Remark |
|-------|:---------------------------:|----------------:|
| sub   |       JWT Issuer Test       |         Subject |
| iss   |             ZXF             |          Issuer |
| ita   |    now(), e.g 1447279113    |       Issued At |
| nbf   |    now(), e.g 1447279113    |      Not Before |
| exp   | now() + 45s, e.g 1447279113 | Expiration Time |
## Custom payloads
| Field     |       Value Example       |     Remark |
|-----------|:-------------------------:|-----------:|
| userId    |           davis           |    User Id |
| orderId   |            123            |   Order Id |
| targetUrl | http://localhost/test-url | Target Url |