
🚀 Twitter Clone API
This project is a comprehensive backend API for a Twitter clone application, built with the Spring Boot framework. It provides a complete set of RESTful endpoints for user management, tweet creation, and interactions like commenting, liking, and retweeting, all secured with JWT-based authentication.

🛠️ About The Project
This API is designed following a layered architecture to ensure a clean separation of concerns, making the codebase maintainable, scalable, and testable.

Core Architecture
Controller Layer: Handles incoming HTTP requests and responses.

Service Layer: Contains all the business logic and rules.

Repository Layer: Manages all database interactions using Spring Data JPA.

Security: Handles authentication and authorization using Spring Security and JSON Web Tokens (JWT).

Mappers & DTOs: Uses DTOs (Data Transfer Objects) and MapStruct for secure and efficient data transfer between layers.

✨ Built With
Spring Boot

Spring Security

Spring Data JPA

PostgreSQL

Maven

JWT (jjwt library)

MapStruct

Lombok

🏁 Getting Started
To get a local copy up and running, follow these simple steps.

Prerequisites
Java 17 or higher

Maven 3.x

PostgreSQL running locally

Installation
Clone the repo

git clone https://github.com/your_username/twitter-api.git

Navigate to the project directory

cd twitter-api

Update the application.properties file with your PostgreSQL credentials.

Build the project with Maven

mvn clean install

Run the application

java -jar target/twitterapi-0.0.1-SNAPSHOT.jar


🚀 Twitter Klonu API
Bu proje, Spring Boot çatısı kullanılarak geliştirilmiş, kapsamlı bir Twitter klonu uygulaması için backend API'sidir. JWT tabanlı kimlik doğrulama ile güvence altına alınmış kullanıcı yönetimi, tweet oluşturma ve yorum yapma, beğenme, retweetleme gibi etkileşimler için eksiksiz bir RESTful endpoint seti sunar.

🛠️ Proje Hakkında
Bu API, Sorumlulukların Ayrılığı (Separation of Concerns) ilkesini sağlamak, kod tabanını sürdürülebilir, ölçeklenebilir ve test edilebilir kılmak amacıyla katmanlı bir mimariyi takip ederek tasarlanmıştır.

Temel Mimari
Controller Katmanı: Gelen HTTP isteklerini ve yanıtlarını yönetir.

Service Katmanı: Tüm iş mantığını ve kurallarını içerir.

Repository Katmanı: Spring Data JPA kullanarak tüm veritabanı etkileşimlerini yönetir.

Güvenlik: Spring Security ve JSON Web Token (JWT) kullanarak kimlik doğrulama ve yetkilendirme işlemlerini yürütür.

Mapper & DTO'lar: Katmanlar arasında güvenli ve verimli veri aktarımı için DTO'lar (Veri Taşıma Nesneleri) ve MapStruct kullanır.

✨ Kullanılan Teknolojiler
Spring Boot

Spring Security

Spring Data JPA

PostgreSQL

Maven

JWT (jjwt kütüphanesi)

MapStruct

Lombok

🏁 Başlarken
Projeyi yerel makinenizde çalıştırmak için aşağıdaki basit adımları izleyin.

Gereksinimler
Java 17 veya üstü

Maven 3.x

Yerel olarak çalışan PostgreSQL

Kurulum
Depoyu klonlayın

git clone https://github.com/kullanici_adiniz/twitter-api.git

Proje dizinine gidin

cd twitter-api

application.properties dosyasını kendi PostgreSQL bilgilerinizle güncelleyin.

Projeyi Maven ile derleyin

mvn clean install

Uygulamayı çalıştırın

java -jar target/twitterapi-0.0.1-SNAPSHOT.jar
