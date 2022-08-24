# Spring Boot RESTful Webservices For Airline Reservation System

<p>This project demonstrate the ability of using and working with Spring Data JPA, REST API, complex Hibernate mapping, 
and in memory database usage for quick test and development.</p>

<p><strong><u>Goal:</u></strong> A complete <strong>Spring Boot RESTful Webservices</strong> project. Built on in memory database so very easy to test. To explore and test endpoints just clone and run the application.</p>

<strong>
<u>Tools used:</u>
</strong>
<ul>
    <li>Spring Boot</li>
    <li>Hibernate</li>
    <li>Jackson</li>
    <li>MySQL (in memory database)</li>
    <li>Maven</li>
</ul>

<strong>
<u>Some Notable Endpoints:</u>
</strong>

<ul>
    <li>localhost:8081/h2 -> correspond to in memory h2 database console.</li>
    <li>GET - localhost:8081/api/public/airports -> returns all Airports.</li>
    <li>GET - localhost:8081/api/public/airlines -> returns all Airlines.</li>
    <li>GET - localhost:8081/api/public/airplanes -> returns all Airplanes.</li>
    <li>GET - localhost:8081/api/public/airline/{airlineName}/airplanes -> returns all Airplanes owned by the given Airline.</li>
    <li>GET - localhost:8081/api/public/customers -> returns all Customers.</li>
    <li>GET - localhost:8081/api/public/flights/{mode} -> returns all Flights.</li>
    <li>GET - localhost:8081/api/public/flights/today/{mode} -> returns all Flights using Current Systems datetime.</li>
    <li>GET - localhost:8081/api/public/flights/{date}/{mode} -> returns all Flights by given date.</li>
    <li>GET - localhost:8081/api/public/flights/fare/{fare}/{mode} -> returns all Flights by given fare.</li>
    <li>GET - localhost:8081/api/public/flights/status/{status}/{mode} -> returns all Flights by given status.</li>
    <li>GET - localhost:8081/api/public/evaluations -> returns all evaluations.</li>
    <li>GET - localhost:8081/api/public/evaluations/{customerId} -> returns all evaluations by customer id.</li>
    <li>GET - localhost:8081/api/public/evaluations/recommend/{customerId} -> returns 3 recommendations for the given customer.</li>    
    <li>GET - localhost:8081/api/public/rsvps/customer/{customerId} -> returns all Reservations by Customer ID.</li>
    <li>GET - localhost:8081/api/public/rsvps/{airline}/active -> returns all ACTIVE Reservations by Airline.</li>
    <li>GET - localhost:8081/api/public/rsvps/{airline}/cancelled -> returns all CANCELLED Reservations by Airline.</li>
    <li>GET - localhost:8081/api/public/rsvps/cancelled -> returns all Reservations that were cancelled.</li>
    <li>POST - localhost:8081/api/public/flight -> insert a Flight into database, does some checking like Loop in Source, Destination - Airports and arrival date must be after the departure date.</li>
    <li>POST - localhost:8081/api/public/rsvp/customer -> makes a reservation for the given customerId with the given flightId. Instead of binding the data, here I used a Map(String, Object) to process this.</li>
    <li>POST - localhost:8081/api/public/login -> Login user. Body request(nome, senha), Header(Autorização)</li>
    <li>POST - localhost:8081/api/public/user -> Creates a user. Body request(nome, email, senha)</li>
    <li>PUT - localhost:8081/api/public/rsvp/cancel/{rsvpId} -> cancel a reservation with the given reservation id.</li>
    <li>PUT - localhost:8081/api/public/flight/cancel/{flightId} -> cancel a flight with the given flight id.</li>
    <li>DELETE - localhost:8081/api/public/delete/customer/{customerId} -> delete a customer associated with the given id, free the reservarions that were made by this customer.</li>
</ul>

Obs: {mode} refers to result order
<ul>
<li>1 -> Lowest fare</li>
<li>2 -> Lowest duration</li>
<li>3 -> Most balanced</li>
<li>Others -> Random</li>
</ul>
