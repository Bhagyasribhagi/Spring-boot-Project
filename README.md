# User Management Spring Boot Application

## Overview

This Spring Boot application exposes four endpoints to manage user data, including creation, retrieval, deletion, and updates. The application uses JPA for database interactions and includes validation for the input data.

## Endpoints
1./get_users
Method: GET

Description: Retrieves user records based on the provided criteria


2./get_user
Method: POST
{
  "userId": "9e1a3a06-2dcb-4863-8712-80fd4ededbd4"
}



//add_user
{
        "fullName": "John",
        "mobNum": "7840478943",
        "panNum": "tcpYy7508P",
        "managerId": "fde03c30-8c58-4817-a482-ca42e007fc45"
}

3. /delete_user
Method: POST

Description: Deletes a user based on the provided user_id or mob_num.
{
    "userId": "f5461cf9-a617-4162-aed9-475aae47fe6c"
}



4. /update_user
Method: POST

Description: Updates user data. Supports bulk update for manager_id only.

Request Body:
[
    {
         "userId": "c447251b-2689-43e4-a538-4ad956174297",
        "fullName": "Shiva Raju",
        "mobNum": "7807697045",
        "panNum": "GoNou0507C",
        "managerId": "fde03c30-8c58-4817-a482-ca42e007fc45"
    }
]
