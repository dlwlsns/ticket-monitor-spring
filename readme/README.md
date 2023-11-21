# Web applications 1 - Project

This project is developed to manage the tickets in a possible "Support service",
the utility of this website is to make easier the management of the tickets to support the clients.

Where full URLs are provided in responses they will be rendered as if service
is running on 'http://localhost:8080/'.

### Current Ticket related

Each endpoint manipulates or displays information related to the Tickets whose
Token is provided with the request:

* [Show Tickets](tickets/get.md) : `GET /tickets/`
* [Show A Ticket](tickets/id/get.md) : `GET /tickets/:id/`
* [Create Ticket](tickets/post.md) : `POST /tickets/`
* [Update A Ticket (full)](tickets/id/put.md) : `PUT /tickets/:id/`
* [Update A Ticket (partial)](tickets/id/patch.md) : `PATCH /tickets/:id/`
* [Delete A Ticket](tickets/id/delete.md) : `DELETE /tickets/:id/`
