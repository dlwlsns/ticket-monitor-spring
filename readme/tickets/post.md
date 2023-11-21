# Create Ticket

Create a Ticket if the authenticated User have permissions.

**URL** : `/tickets/`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Provide name, description and author of Ticket to be created.
All fields must be sent.

```json
{
    "title": "Laptop not connecting to Wi-Fi",
    "description": "Laptop model XYZ does not connect to the local network.",
    "author": "Pippo"
}
```


## Success Response

**Condition** : If everything is OK and a Ticket was created.

**Code** : `201 CREATED`

**Content example**

```json
{
    "id": "3qu4hgoiu4",
    "title": "Laptop not connecting to Wi-Fi",
    "description": "Laptop model XYZ does not connect to the local network.",
    "author": "Pippo"
}
```

## Error Responses

**Condition** : If fields are missed.

**Code** : `400 BAD REQUEST`

