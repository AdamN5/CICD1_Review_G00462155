# CICD-1 Coding Test — Review, Improve and Reflect

**Student ID:**  G00462155

**Examination group:** A (Room Booking / JPQL) 

**Original examination pull-request URL (`exam-work`):**  https://github.com/AdamN5/G00462155/pull/1

**New review repository URL:**  https://github.com/AdamN5/CICD1_Review_G00462155

## 1. My original examination self-assessment

Review the code **as submitted in the original examination**. Do not change the original repository, branch or pull request. Check your own Group A or Group B examination paper and award marks only for evidence present in the original submission.

### Task 1 — /40
**My mark:** 12/40
**Evidence from the original code (files, methods, working or missing requirements):**  The model Booking has "@ManyToOne @JoinColumn(name="room_id" Room room" and has "organiserName" "attendeeCount".
Theres no validation for anything.
No seperate DTO.
"status" is a boolean instead of a status value, and theres no default.

### Task 2 — /40
**My mark:**  3/40
**Evidence from the original code:**  A booking would be saved with no values as "create(Booking booking)" has no @RequestBody.
Noo room lookup or capacity check. 
I only wrote "getAll()" and needed to get by booking ids.
No error handling.

### Task 3 — /20
**My mark:**  0/20
**Evidence from the original code:**  BookingRepository is empty so there is no ordering by attendee count and no search endpoint.

**Total self-assessed original examination mark: 15/100**

**Checkpoint:** Commit this completed self-assessment as the **first review commit in your new repository**, before you modify or add application code. The lecturer decides the official original examination mark.

## 2. Corrections and improvements in my new project

Start from a **fresh copy of the starter template for your own exam group**, in a **new GitHub repository**. You may consult or selectively reuse your own original examination code, but the new project must demonstrate that you understand and can correct it. Do not change your original examination submission.

Describe **two or three important problems** in your original code. For each, explain what was wrong, which class or method you changed in the new project, and why the corrected approach works.

**Problem and correction 1:**  

Problem:
Booking entity was returned direct without validation and with a boolean status.

Fix: 
Added BookingRequest and BookingResponse DTOs with proper validation. Added BookingStatus enum which defaults to active.

**Problem and correction 2:**  

Problem:
My POST api/booking endpoint had no @RequestBody and no capacity check and no GET by id and no error handling.

Fix: 
BookingController now uses api/bookings. BookingService checks the room is there and the capacity with error handling if nothing exists.


**Problem and correction 3 (optional):** 

Problem:
No JPQL search. 

Fix: 
BookingRepository.findByRoomName uses @Query with WHERE b.room.name = :roomName ORDER BY b.attendeeCount DESC, which gives an exact match, highest first, and [] if nothing matches.


## 3. API testing and evidence

Use Swagger UI or another HTTP client. For **at least four meaningful checks**, record the request/input, expected result, actual HTTP status/response, and pass/fail. Include unsuccessful cases; a running application alone is not enough.

**Test 1 — successful booking request:**  
- Request / input:  POST /api/bookings
{
  "roomId": 1,
  "organiserName": "Adamn",
  "attendeeCount": 6
}
- Expected:  201  with id, roomId 1, roomName "Lab 201", organiserName Adamn, attendeeCount 6, status "ACTIVE"
- Actual HTTP status / body:  201
}
  "id": 5,
  "roomId": 1,
  "roomName": "Lab 201",
  "organiserName": "Adamn",
  "attendeeCount": 6,
  "status": "ACTIVE"
}
- Pass / fail:  Pass

**Test 2 — invalid input or excessive attendee count:**  
- Request / input: POST /api/bookings
{
  "roomId": 2,
  "organiserName": "Adamn",
  "attendeeCount": 0
}
- Expected:  400
- Actual HTTP status / body:  400
{
  "status": 400,
  "message": "Attendee count must be positive"
}
- Pass / fail:  Pass

**Test 3 — missing booking or room:**  
- Request / input: GET /api/bookings/{id}
GET /api/bookings/999999
- Expected:  404
- Actual HTTP status / body:  404
{
  "status": 404,
  "message": "Booking not found with ID: 999999"
}
- Pass / fail:  Pass

**Test 4 — requirement specific to my group:**  
- Request / input:  GET /api/bookings/search
/api/bookings/search?roomName=Lab%20201
- Expected:  200
List of bookings for room with bookings ordered by attendee count descending.
- Actual HTTP status / body: 200
[
  {
    "id": 2,
    "roomId": 1,
    "roomName": "Lab 201",
    "organiserName": "Aoife Byrne",
    "attendeeCount": 18,
    "status": "ACTIVE"
  },
  {
    "id": 1,
    "roomId": 1,
    "roomName": "Lab 201",
    "organiserName": "Adam",
    "attendeeCount": 10,
    "status": "ACTIVE"
  },
  {
    "id": 4,
    "roomId": 1,
    "roomName": "Lab 201",
    "organiserName": "Bill",
    "attendeeCount": 6,
    "status": "ACTIVE"
  },
]
- Pass / fail:  Pass

**Group A:** Demonstrate `GET /api/bookings/search?roomName=...` using an exact room name, bookings ordered by attendee count descending, and an empty list for no matches. Add checks if necessary to show all three behaviours.

## 4. Reflection

**What did I understand during the examination but struggle to implement?**  

I understood that bookings belong to rooms and should return DTOs but I ran out of time to understand everything fully and connect the pieces. I spent too much time looking at previous labs and was out of practice from the reading week.
I never wrote the room look up, checks or the search and forgot @RequestBody.

**Which Spring Boot, JPA, validation, JPQL or OpenFeign concept became clearer after correcting my code? Give a specific example.**  

Learned how JPQL searches work. in findByRoomName I can search bookings by the room name and the results come back with descending attendee count.

**What will I do differently in my next coding test or API development task?**  

I would practice better and only saw the the practice repository minutes before the test and struggled to read the paper as I was under pressure. In short I would practice well in advance, stay calm and read the paper better.

## Review lab assessment — 20 marks

- Honest original self-assessment with code evidence: **4 marks**
- Explanation of mistakes and Spring Boot concepts: **6 marks**
- Corrections in the new application: **4 marks**
- API testing and demonstration of understanding: **4 marks**
- Reflection and future improvement: **2 marks**

**Total: 20 marks.** Simply submitting a working solution does not demonstrate the learning required by this review.

## Proposed mark recovery — only if confirmed for CICD-1

If the same approach as OOP is adopted, the review could recover **up to 30% of marks lost** in the original examination, scaled by the review-lab mark.

**Marks recovered = (100 − official original exam mark) × 0.30 × (review lab mark ÷ 20)**

Example: exam **40/100** and review **16/20** → **14.4 recovered marks**, revised exam **54.4/100**. This is **not an approved CICD-1 policy unless confirmed by the lecturer**. The original official exam mark—not your self-awarded grade—is used in the calculation.

## Submission

Commit and push the **new review repository** with the completed README, corrected Spring Boot code and API-test evidence. Submit its GitHub URL on Moodle. **Do not push any changes to the original examination repository or `exam-work` pull request.**
