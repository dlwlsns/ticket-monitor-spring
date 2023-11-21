# Delete Ticket

Delete a Ticket.

**URL** : `/tickets/:id/`

**URL Parameters** : `id=[string]` where `id` is the ID of the Ticket in the
database.

**Method** : `DELETE`

**Auth required** : NO

**Permissions required** : None

**Data** : `{}`

## Success Response

**Condition** : If the Ticket exists.

**Code** : `204 NO CONTENT`

**Content** : `{}`

## Error Responses

**Condition** : If there was no Ticket available to delete.

**Code** : `404 NOT FOUND`

**Content** : `{}`
