#List of User
GET http://localhost:8080/user/getlist

#Add User
POST http://localhost:8080/user/add
{
    "emailId": "navnit12@mail.com"
}

#Update User
POST http://localhost:8080/user/update
{
    "id":"17",
    "emailId": "rnavnit1@gmail.com"
}

#Delete User
GET http://localhost:8080/user/delete/{email}


#List of Team
GET http://localhost:8080/team/getlist

#Add Team
POST http://localhost:8080/team/add
{
    "teamName":"ABC"
}

#Update Team
POST http://localhost:8080/team/update
{
    "id": 4,
    "teamName": "XYZ"
}

#Delete Team
GET http://localhost:8080/team/delete/{teamName}
