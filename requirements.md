abbr[FE=FRONT END,]

Requirements 

Reservation 

Seats and Passangers 

Passanger can reserve seats as per the route regardless of the trip(Customer XYS choses Segment A to B, Customer YXS chooses B to D, all of the charged per their travel)

All passangers per reservation can travel on a single ticket or can request tickets per each

*Constraints 
Always a seat per passanger maintained, regardless of age, gender or any other criteria 


Reservation API

The following improvements were identified on the flow to improve the performance and minimize the unnecessary complixity in the business logics. The flow improvement purely focuses on the reservation flow assuming all other necessary prerequilits like login are all satisfied. Please note that below demonstration focuses only on the happy path scenarios.

Improvements 

User logins
.
.
.
User lands on reservation page
.
.
.
Selects date for reservation, enters number of seats  ==> FE calls API ==> GET: /api/bookings/check-availability-price <= retrieves seats that are selected on the given criteris (improvement point 1)
.
.
.
Selects seats according to the avialbility from previous API call [ out of scope improvement to enable advanced mechanisms to lock seats upon selection ]
.
.
.
User reserves selected seats (inputs Selected Seats, Selected Trip, Origin, Destination, Payment Details) ==> FE calls API ==> POST:/api/booking/reservation
.
.
.
User Redirected for Payments


![alt text](image.png)

