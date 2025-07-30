Of course. Based on our deep dive into your "Vastral" application's codebase and its underlying business logic, I have completely upgraded and updated your README file.

This new version is professional, technically accurate, and designed to impress anyone viewing your GitHub profile. It correctly represents your Spring Boot backend, highlights the project's unique features (like OTP auth and multi-vendor logic), and provides clear instructions for setup and use.

---

# Vastral 🚀 - Multi-Vendor Quick Commerce Platform

[![Java](https://img.shields.io/badge/Language-Java-blue?style=for-the-badge&logo=java)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Framework-Spring_Boot-green?style=for-the-badge&logo=spring)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/Database-MySQL-orange?style=for-the-badge&logo=mysql)](https://www.mysql.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

## Overview 👗

Vastral is a hyper-local, multi-vendor quick-commerce platform engineered to connect local clothing stores with customers seeking fashion on demand. The core idea is to empower local businesses while providing users with a seamless mobile shopping experience for purchasing or renting trendy outfits, with delivery in minutes.

This project is a complete, full-stack application featuring a secure, scalable backend built with Spring Boot and a dynamic front-end intended for React Native.

## Core Concepts & Architecture

*   **Multi-Vendor Ecosystem:** Vastral is built on a multi-tenant architecture that allows multiple independent sellers (local stores) to register, manage their own inventory, process orders, and view sales reports. This creates a scalable business model where the platform grows as more vendors join.

*   **API-First Design:** The application is architected with a "headless" approach. A robust, stateless RESTful API serves as the central nervous system, capable of supporting any front-end client, be it the primary React Native mobile app, a future web application, or third-party integrations.

## Key Features ✨

#### 🛍️ For Customers

*   **Secure Password-less Authentication:** Modern, secure user experience with OTP-based registration and login, eliminating the need for traditional passwords.
*   **Advanced Product Browsing:** Users can browse products, filter by category, price, and color, and use a search functionality.
*   **Comprehensive Shopping Cart:** Fully functional cart that intelligently handles items from multiple different sellers within a single checkout flow.
*   **Multi-Seller Order Management:** A single transaction is automatically split into separate orders for each seller involved, which the user can track individually.
*   **Wishlist & Reviews:** Ability to save items for later in a wishlist and provide product ratings and reviews.

#### 💼 For Sellers

*   **Self-Service Onboarding:** A complete registration and email verification workflow for new sellers.
*   **Vendor Dashboard:** Secure, role-protected endpoints allowing sellers to manage their business.
*   **Full Product Lifecycle Management:** Sellers can create, view, update, and delete their own product listings.
*   **Order Fulfillment:** Sellers can view and update the status of orders corresponding only to their products (e.g., Confirmed, Shipped).

#### 🔑 For Admins

*   **Platform Moderation:** Admin-level authority to manage sellers by viewing their status (Pending, Active, Suspended) and taking necessary actions.
*   **Promotions Engine:** Ability to create and manage platform-wide discount coupons with specific rules (e.g., minimum order value, validity dates) to drive sales.
*   **Deal Management:** Admins can create and feature special deals linked to specific homepage categories.

## Tech Stack 💻

| Category            | Technology                                                              |
| ------------------- | ----------------------------------------------------------------------- |
| **Backend**         | **Spring Boot**, **Spring Security (JWT, RBAC)**, **Spring Data JPA (Hibernate)**, Java 17, Maven |
| **Database**        | **MySQL**                                                               |
| **API Documentation** | **SpringDoc OpenAPI 3** (Swagger UI)                                    |
| **Frontend**        | **React Native**                                                        |
| **Services**        | **Spring Mail Sender** (for OTP delivery)                               |

## API Documentation 📜

The entire REST API is professionally documented and can be explored interactively via Swagger UI. Once the backend is running, visit:

**[http://localhost:5454/swagger-ui.html](http://localhost:5454/swagger-ui.html)**

> You can use the "Authorize" button on the page to use the JWT token obtained after login, allowing you to test protected endpoints directly from your browser.

## Getting Started 🛠️

### Prerequisites
*   Java JDK 17 or later
*   Apache Maven
*   MySQL Server
*   Node.js and npm (for the React Native front-end)
*   A Gmail account with an **App Password** enabled (for the email service).

### Backend Setup
1.  **Clone the Repository**
    ```bash
    git clone https://github.com/MahaDev-73/Vastral.git
    cd Vastral # Navigate to the root directory of the Spring Boot project
    ```

2.  **Configure the Database & Mail Service**
    *   Open `src/main/resources/application.properties`.
    *   Update the `spring.datasource.*` properties with your MySQL username and password.
    *   Update the `spring.mail.*` properties with your Gmail email and the 16-digit **App Password** (do not use your regular account password).

3.  **Run the Application**
    *   You can run the application using your IDE (like Eclipse or IntelliJ) or via the command line:
    ```bash
    mvn spring-boot:run
    ```    The backend server will start on `http://localhost:5454`.

### Frontend Setup
1.  **Navigate to the Frontend Directory** (Assuming it's within the project)
    ```bash
    cd ../vastral-frontend # Example directory name
    ```
2.  **Install Dependencies**
    ```bash
    npm install
    ```
3.  **Run the App**
    ```bash
    npx react-native run-android
    # or
    npx react-native run-ios
    ```

## Usage & API Workflow 🛒
A common workflow for testing the API (e.g., with Postman) is as follows:
1.  **Request an OTP** for a user or seller.
2.  **Register or Log in** using the OTP to receive a JWT.
3.  **Store the JWT** and include it in the `Authorization` header for all protected API calls (e.g., `Authorization: Bearer <your_jwt>`).
4.  Proceed to test role-specific functionalities like adding items to a cart (customer) or creating a product (seller).

## Contributing 🤝
Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

Please fork the repository and submit a pull request with your changes. Ensure you follow the project's coding standards and include appropriate tests.

## Contact 📧
Prince Charming - [princecharming8869@gmail.com](mailto:princecharming8869@gmail.com)

Project Link: [https://github.com/MahaDev-73/Vastral](https://github.com/MahaDev-73/Vastral)
