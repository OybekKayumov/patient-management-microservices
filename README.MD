# Patient Management System

A **Patient Management System** built with a microservices architecture using **Java and Spring Boot**.

## Architecture

The project consists of several independent services:

* **Patient Service** — patient management
* **Auth Service** — authentication and authorization
* **Billing Service** — billing operations
* **Analytics Service** — analytics and reporting
* **API Gateway** — single entry point for client requests
* **Integration Tests** — integration testing
* **Infrastructure** — infrastructure and AWS/CDK configuration

## Technologies

* Java 21
* Spring Boot
* Spring Security
* PostgreSQL
* Apache Kafka
* gRPC
* Docker
* AWS CDK
* LocalStack
* Swagger / OpenAPI
* Maven

## Running the Project

Clone the repository:

```bash
git clone https://github.com/OybekKayumov/patient-management-microservices.git
cd patient-management-microservices
```

Start the required infrastructure with Docker Compose and run the microservices.

For local AWS infrastructure, the project uses **LocalStack** and AWS CDK.

## Project Structure

```text
patient-management-microservices/
├── patient-service/
├── auth-service/
├── billing-service/
├── analytics-service/
├── api-gateway/
├── api-requests/
├── grpc-requests/
├── integration-tests/
└── infrastructure/
```

## Purpose

This project is a practical microservices application demonstrating:

* REST APIs
* Authentication and authorization
* Event-driven communication with Kafka
* gRPC communication
* API Gateway
* Database integration
* Integration testing
* Containerization with Docker
* Infrastructure as Code with AWS CDK

## 2026 09
