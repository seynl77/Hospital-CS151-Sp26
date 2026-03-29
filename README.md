# Hospital-CS151-Sp26
## Overview
This project simulates a hospital management system using Java. It allows hospital staff to manage patients, doctors, appointments, rooms, and billing efficiently through a console-based interface.
The system ensures proper scheduling, prevents conflicts, and maintains patient and hospital records systematically. 
The system includes integrated billing and room management features as part of the main workflow.


## Design


Main classes include:
- Hospital
- Person 
- Patient
- Doctor
- Bill
- Room
- Appointment
- Main

These classes interact with each other to simulate hospital operations.

## Installation Instructions
1. Clone the repository:
https://github.com/seynl77/Hospital-CS151-Sp26
2. Open the project in your preferred IDE (VSCode / IntelliJ / Eclipse).
3. Compile and run the `Main.java` file.

## Usage
Once running, you interact with the system via a console menu.

Main Menu Options
- Patient Management – Add, view, admit, discharge, update medical history.
- Doctor Management – Add and view doctors.
- Appointment Management – Create, view, cancel, and complete appointments.
- Room Management – Add rooms, assign patients, vacate, view info, and mark maintenance.
- Billing Management – Generate bills, add charges, apply discounts, pay bills, and track bill status.
- View Hospital Info – Displays general hospital information.
- Exit – Close the application.

Example Interaction:

Add a patient → Add a doctor → Create an appointment → Complete appointment → Generate bill.

## Contributions
Elaine
- Patient class
- Appointment class
- Initial repository setup
- Appointment function in Main Class
- UML

Andrew
- Doctor class
- Implemented hospital in main
- Misc fixes across all classes

Derek
- Person class
- Hospital class
- Main class

Serife Aynur Kocdas
- Implemented Bill class and integrated billing functionality into the system
- Implemented Room class and room management features
- Created Billable interface
- Implemented custom exceptions:
  - InvalidAmountException
  - InvalidOperationException
  - MaxCapacityException
- Added BillRoomManualTest.java
- Contributed to Main.java integration for billing and room functionality

