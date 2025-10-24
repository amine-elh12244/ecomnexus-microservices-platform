# 🛒 EcomNexus – Plateforme E-commerce Microservices

## 🚀 Description du projet

**EcomNexus** est une plateforme e-commerce distribuée basée sur une **architecture microservices** moderne.  
Elle a pour objectif de démontrer la conception, l’orchestration et la communication entre plusieurs services indépendants dédiés à la gestion d’un système e-commerce complet.

L’application est composée de **4 microservices principaux** :
- 🧍 **Clients Service** – Gestion des utilisateurs et profils clients.  
- 📦 **Produits Service** – Gestion du catalogue produits et des stocks.  
- 🧾 **Commandes Service** – Gestion des commandes et suivi du panier.  
- 💳 **Paiements Service** – Traitement des paiements et intégration des transactions.

---

## ⚙️ Architecture et communication

L’architecture suit les principes d’un **système distribué** :
- Communication **asynchrone** entre microservices via **Apache Kafka**, garantissant la traçabilité et la résilience des événements.  
- **Spring Cloud** assure la configuration centralisée, la découverte de services et la tolérance aux pannes.  
- **Docker Compose** orchestre les conteneurs pour offrir un déploiement reproductible et isolé.  
- **Flyway** gère les migrations de schémas pour les bases **PostgreSQL** et **MongoDB** dans un environnement multi-base.  

---

## 🧰 Technologies utilisées

| Domaine | Technologies |
|----------|---------------|
| **Backend** | Java 17, Spring Boot, Spring Cloud, Spring Data |
| **Bases de données** | PostgreSQL, MongoDB |
| **Messagerie** | Apache Kafka |
| **Migrations** | Flyway |
| **Conteneurisation & orchestration** | Docker, Docker Compose |
| **Monitoring & logs** | Spring Boot Actuator, Kafka UI, centralisation des logs |

---

## 🏗️ Architecture globale

