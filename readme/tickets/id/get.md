# Show Single Ticket

Show a single Ticket if current User has access permissions to it.

**URL** : `/tickets/:id/`

**URL Parameters** : `id=[string]` where `id` is the ID of the Ticket on the
server.

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data**: `{}`

## Success Response

**Condition** : If Ticket exists and Authorized User has required permissions.

**Code** : `200 OK`

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

**Condition** : If Ticket does not exist with `id` provided.

**Code** : `404 NOT FOUND`

**Content** : `{}`
