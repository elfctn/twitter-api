Twitter Clone API
This project is a comprehensive backend API for a Twitter clone application, built with Spring Boot. It offers RESTful endpoints for user management, tweet creation, and interactions like commenting, liking, and retweeting — all secured with JWT-based authentication.

🔍 About The Project
This API is built using a layered architecture to ensure maintainability, scalability, and testability.

Controller Layer: Handles HTTP requests and responses.

Service Layer: Encapsulates business logic.

Repository Layer: Manages data access with Spring Data JPA.

Security: Handles JWT-based authentication and authorization using Spring Security.

DTOs & Mappers: Uses MapStruct and DTOs for efficient data transfer.

⚙️ Built With
Spring Boot

Spring Security

Spring Data JPA

PostgreSQL

Maven

JWT (jjwt)

MapStruct

Lombok

🚀 Getting Started
✅ Prerequisites
Java 17+

Maven 3.x

PostgreSQL running locally

📥 Installation
bash
Kopyala
Düzenle
git clone https://github.com/your_username/twitter-api.git
cd twitter-api
Update your PostgreSQL credentials in src/main/resources/application.properties.

bash
Kopyala
Düzenle
mvn clean install
java -jar target/twitterapi-0.0.1-SNAPSHOT.jar
📄 README.md (Türkçe)
Twitter Klonu API
Bu proje, Spring Boot ile geliştirilen kapsamlı bir Twitter klonu backend API'sidir. JWT tabanlı güvenlik ile korunmuş kullanıcı yönetimi, tweet oluşturma, yorum yapma, beğeni ve retweet gibi işlemleri gerçekleştirmek için RESTful servisler sunar.

🔍 Proje Hakkında
Bu API, katmanlı mimari prensibi ile yapılandırılmıştır.

Controller Katmanı: HTTP isteklerini ve yanıtlarını yönetir.

Service Katmanı: İş kurallarını ve mantığını içerir.

Repository Katmanı: Veritabanı işlemlerini Spring Data JPA ile yürütür.

Güvenlik: JWT ve Spring Security ile kimlik doğrulama ve yetkilendirme sağlar.

DTO & Mapper: Veri aktarımında MapStruct ve DTO yapısı kullanır.

⚙️ Kullanılan Teknolojiler
Spring Boot

Spring Security

Spring Data JPA

PostgreSQL

Maven

JWT (jjwt)

MapStruct

Lombok

🚀 Başlarken
✅ Gereksinimler
Java 17 veya üzeri

Maven 3.x

Yerel PostgreSQL sunucusu

📥 Kurulum
bash
Kopyala
Düzenle
git clone https://github.com/kullanici_adiniz/twitter-api.git
cd twitter-api
application.properties dosyasındaki veritabanı bilgilerini kendi bilgilerinizle değiştirin.

bash
Kopyala
Düzenle
mvn clean install
java -jar target/twitterapi-0.0.1-SNAPSHOT.jar
