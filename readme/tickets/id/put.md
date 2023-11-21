# Full Ticket Update

Fully update a Ticket if the authenticated User have permissions.

**URL** : `/tickets/:id`

**URL Parameters** : `id=[string]` where `id` is the ID of the Ticket in the
database.

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Provide name, description and author of Ticket to be updated.
All fields must be sent.

```json
{
    "title": "Laptop not connecting to Wi-Fi v5",
    "description": "Laptop model XYZ does not connect to the local network and also external networks.",
    "author": "Pippo"
}
```


## Success Response

**Condition** : If everything is OK and a Ticket was updated.

**Code** : `200 OK`

**Content example**

```json
{
    "id": "3qu4hgoiu4",
    "title": "Laptop not connecting to Wi-Fi v5",
    "description": "Laptop model XYZ does not connect to the local network and also external networks.",
    "author": "Pippo"
}
```

## Error Responses

**Condition** : If fields are missed.

**Code** : `400 BAD REQUEST`

