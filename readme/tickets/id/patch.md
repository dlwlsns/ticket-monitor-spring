# Partial Ticket Update

Partially update a Ticket if the authenticated User have permissions.

**URL** : `/tickets/:id`

**URL Parameters** : `id=[string]` where `id` is the ID of the Ticket in the
database.

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Provide name, description and author of Ticket to be updated.
Not all fields must be sent.

```json
{
    "title": "Laptop not connecting to Wi-Fi v5"
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
    "description": "Laptop model XYZ does not connect to the local network",
    "author": "Pippo"
}
```

## Error Responses

**Condition** : If `id` does not exists or not provided.

**Code** : `400 BAD REQUEST`

