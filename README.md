# LTCHackathon

# AccountController
# Get Account Balance
URL: /api/accounts/{accountId}/balance
Method: GET
Path Variables:
accountId (Long): The ID of the account.
Response:
200 OK: Returns the account details including balance.
Example Request:
curl -X GET http://localhost:8080/api/accounts/1/balance
Example Response:
{
  "id": 1,
  "balance": 1000.0
}
# Get User Accounts
URL: /api/accounts/{userId}/accounts
Method: GET
Path Variables:
userId (Long): The ID of the user.
Response:
200 OK: Returns a list of accounts for the user.
Example Request:
curl -X GET http://localhost:8080/api/accounts/1/accounts
Example Response:
[
  {
    "id": 1,
    "accountNumber": "123456"
  },
  {
    "id": 2,
    "accountNumber": "654321"
  }
]
# UserController
# Get User
URL: /api/users/{userId}
Method: GET
Path Variables:
userId (Long): The ID of the user.
Response:
200 OK: Returns the user details.
Example Request:
curl -X GET http://localhost:8080/api/users/1
Example Response:
{
    "id": 1,
    "username": "chiranjeevih",
    "password": "$2a$10$IgTv68gV1itPFNLMROzfRewzUlNdSvrIkHZOOMM3QxTANPjwRDQ52",
    "email": "chiranjeevih@gmail.com",
    "phone": "1234567890",
    "fullName": "Chiranjeevi Haridasula",
    "dateOfBirth": "1993-07-12",
    "address": {
        "id": 1,
        "street": "123 Main St",
        "city": "Hyderabad",
        "state": "Telangana",
        "zipCode": null,
        "country": null
    }
}
# Register User
URL: /api/users/register
Method: POST
Request Body:
User object containing user details.
Response:
200 OK: Returns the registered user details.
Example Request:
curl -X POST http://localhost:8080/api/users/register -H "Content-Type: application/json" -d '
{
    "id": 1,
    "username": "chiranjeevih",
    "password": "$2a$10$IgTv68gV1itPFNLMROzfRewzUlNdSvrIkHZOOMM3QxTANPjwRDQ52",
    "email": "chiranjeevih@gmail.com",
    "phone": "1234567890",
    "fullName": "Chiranjeevi Haridasula",
    "dateOfBirth": "1993-07-12",
    "address": {
        "id": 1,
        "street": "123 Main St",
        "city": "Hyderabad",
        "state": "Telangana",
        "zipCode": null,
        "country": null
    }
}
}'
Example Response:
{
    "id": 1,
    "username": "chiranjeevih",
    "password": "$2a$10$IgTv68gV1itPFNLMROzfRewzUlNdSvrIkHZOOMM3QxTANPjwRDQ52",
    "email": "chiranjeevih@gmail.com",
    "phone": "1234567890",
    "fullName": "Chiranjeevi Haridasula",
    "dateOfBirth": "1993-07-12",
    "address": {
        "id": 1,
        "street": "123 Main St",
        "city": "Hyderabad",
        "state": "Telangana",
        "zipCode": null,
        "country": null
    }
}
# Login User
URL: /api/users/login
Method: POST
Request Body:
User object containing username and password.
Response:
200 OK: Returns a login success message.
Example Request:
curl -X POST http://localhost:8080/api/users/login -H "Content-Type: application/json" -d '{
  "username": "chiranjeevih",
  "password": "password123"
}'
Example Response:
{
  "message": "Login successful"
}
# Logout User
URL: /api/users/logout
Method: POST
Response:
200 OK: Returns a logout success message.
Example Request:
curl -X POST http://localhost:8080/api/users/logout
Example Response:
{
  "message": "Logout successful"
}
# TransactionController
Get Transaction
URL: /api/transactions/{transactionId}
Method: GET
Path Variables:
transactionId (Long): The ID of the transaction.
Response:
200 OK: Returns the transaction details.
Example Request:
curl -X GET http://localhost:8080/api/transactions/1
Example Response:
{
  "id": 1,
  "amount": 100.0,
  "date": "2023-01-01",
  "description": "Payment"
}
# Create Transaction
URL: /api/transactions
Method: POST
Request Body:
Transaction object containing transaction details.
Response:
200 OK: Returns the created transaction details.
Example Request:
curl -X POST http://localhost:8080/api/transactions -H "Content-Type: application/json" -d '{
  "amount": 100.0,
  "date": "2023-01-01",
  "description": "Payment"
}'
Example Response:
{
  "id": 1,
  "amount": 100.0,
  "date": "2023-01-01",
  "description": "Payment"
}
# Get User Transactions
URL: /api/transactions/user/{userId}
Method: GET
Path Variables:
userId (Long): The ID of the user.
Response:
200 OK: Returns a list of transactions for the user.
Example Request:
curl -X GET http://localhost:8080/api/transactions/user/1
Example Response:
[
  {
    "id": 1,
    "amount": 100.0,
    "date": "2023-01-01",
    "description": "Payment"
  },
  {
    "id": 2,
    "amount": 200.0,
    "date": "2023-01-02",
    "description": "Refund"
  }
]
