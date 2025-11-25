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

# JWT.IO
- https://www.jwt.io/

# Javascript Object Signing and Encryption(JOSE)
- Javascript Object Signing and Encryption(JOSE) is the overarching framework.
- JSON Web Signature(JWS) and JSON Web Encryption(JWE) are the secure containers—one is signed, the other is encrypted.
- JSON Web Algorithms(JWA) and JSON Web Keys(JWK) are the tools—the algorithms and keys used to secure the containers.
- JSON Web Token(JWT) is the content—the standardized set of claims you put inside the container.
