# Show Accessible Accounts

Show all Tickets the active User can access and with what permission level.

**URL** : `/tickets/`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : User can not see any Tickets.

**Code** : `200 OK`

**Content** : `{[]}`

### OR

**Condition** : User can see one or more Tickets.

**Code** : `200 OK`

**Content** : In this example, the User can see three Tickets:

```json
[
    {
        "id": "3qu4hgoiu4",
        "title": "Laptop not connecting to Wi-Fi",
        "description": "Laptop model XYZ does not connect to the local network.",
        "author": "Pippo"
    },
    {
        "id": "98q4zjo",
        "title": "Application crash",
        "description": "Latest update of application keeps crashing.",
        "author": "Topolino"
    },
    {
        "id": "38484tji",
        "title": "Tablet replacement",
        "description": "My tablet does not work anymore, need a new one.",
        "author": "Ermenegildo"
    }
]
```
