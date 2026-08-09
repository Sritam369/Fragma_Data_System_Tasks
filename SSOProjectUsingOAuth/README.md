# Google OAuth2 SSO Project

## Project Description

This project implements Single Sign-On (SSO) using Google OAuth2 in a Spring Boot application.

## Technologies Used

- Java 21
- Spring Boot 4.0.7
- Spring Security
- Google OAuth2
- Spring Data JPA
- Oracle Database
- Thymeleaf
- Maven

## Features

- Google OAuth2 Login
- New user registration
- Existing user detection using email
- User profile page
- Logout functionality
- User details stored in the database
- Google profile picture display

## User Flow

1. User opens the Home page.
2. User clicks **Login with Google**.
3. User is redirected to Google for authentication.
4. After successful authentication, the application receives the user's Google details.
5. The application checks whether the user's email exists in the database.
6. If the user already exists, the user is redirected to the Profile page.
7. If the user is new, the Registration page is displayed.
8. The user enters:
   - Phone Number
   - Department
   - Designation
9. The user details are saved in the database.
10. The user is redirected to the Profile page.
11. The user can Logout and return to the Home page.

## Database

The user details are stored in the `USER_DETAILS_DB` table.

| Field | Description |
|---|---|
| ID | Primary Key |
| EMAIL | Unique email address |
| NAME | User name |
| PHOTO_URL | Google profile picture URL |
| PHONE | Phone number |
| DEPARTMENT | Department |
| DESIGNATION | Designation |

## New User Detection

After successful Google authentication, the application retrieves the user's email address.

The application checks whether the email already exists in the database.

- **Email exists** → Profile page
- **Email does not exist** → Registration page

The email is configured as a unique field in the database to prevent duplicate users.

## Google OAuth2

Google OAuth2 is used for authentication.

The application requests the following scopes:

- `openid`
- `email`
- `profile`

The Google profile provides information such as:

- Name
- Email
- Profile Picture

## Application Port

The application runs on:

```text
http://localhost:4041