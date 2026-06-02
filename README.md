# 🛒 TechSahil E-Commerce Project

![Java](https://img.shields.io/badge/Backend-Java-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Framework-green?style=for-the-badge&logo=springboot)
![React](https://img.shields.io/badge/Frontend-React-blue?style=for-the-badge&logo=react)
![MySQL](https://img.shields.io/badge/Database-MySQL-orange?style=for-the-badge&logo=mysql)
![Workbench](https://img.shields.io/badge/MySQL-Workbench-lightblue?style=for-the-badge&logo=mysql)
![Status](https://img.shields.io/badge/Project-Practice-yellow?style=for-the-badge)

## 📌 Project Overview

TechSahil E-Commerce is a full-stack practice project developed using React (Frontend) and Spring Boot (Backend) with MySQL Workbench as the database tool.

This project simulates a real-world e-commerce application and demonstrates how frontend, backend, and database interact together.

It helps in understanding REST API development, frontend-backend integration, and database management using MySQL Workbench.

## 🏗️ Project Architecture

Frontend (React) ---> Backend (Spring Boot REST APIs) ---> Database (MySQL Workbench)

## 📁 Project Structure

techsahil-ecommerce/
│
├── backend/
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   └── config
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   └── App.js
│   ├── public/
│   └── package.json
│
└── README.md

## ⚙️ Tech Stack

Backend:
- Java
- Spring Boot
- Spring Data JPA
- REST APIs
- Hibernate

Frontend:
- React.js
- JavaScript (ES6+)
- Axios
- HTML5
- CSS3

Database:
- MySQL
- MySQL Workbench

## 🚀 Features

- Product CRUD operations
- Product listing UI in React
- Add / Update / Delete products
- REST API integration (React ↔ Spring Boot)
- MySQL database integration via Workbench
- Real-time UI updates using React

## ▶️ How to Run the Project

### 1. Clone Repository
git clone https://github.com/Tech-SahilSh/TechSahil-ECommerce.git

### 2. Database Setup (MySQL Workbench)
CREATE DATABASE ecommerce;

### 3. Backend Configuration (application.properties)
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

### 4. Run Backend
mvn spring-boot:run

Backend runs at:
http://localhost:8080

### 5. Frontend Setup

cd frontend
npm install
npm start

Frontend runs at:
http://localhost:3000

## 🔗 API Endpoints

GET /products → Fetch all products  
POST /products → Add product  
PUT /products/{id} → Update product  
DELETE /products/{id} → Delete product  

## 📚 What I Learned

- Full-stack development workflow
- Spring Boot REST API creation
- React frontend development
- Axios API integration
- MySQL Workbench usage
- CRUD operations
- Git & GitHub project management

## 🎯 Future Improvements

- JWT Authentication system
- Shopping cart & checkout system
- Payment gateway integration
- UI improvements using Tailwind/Material UI
- Cloud deployment (AWS / Render / Vercel)

## 👨‍💻 Developer

Sahil Sheikh  
MCA Graduate | Full Stack Java Developer  
Nagpur, India  

⭐ If you like this project, give it a star!
