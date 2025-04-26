# PetTracker - Spring Boot Project

This project is a simple RESTful API application developed using Spring Boot to manage pet information. It handles the relationship between owners and their pets and provides basic CRUD (Create, Read, Update, Delete) operations.

---

Bu proje, Spring Boot kullanarak evcil hayvan bilgilerini yönetmek için geliştirilmiş  bir RESTful API uygulamasıdır. Kullanıcılar (sahipler) ve onların evcil hayvanları arasındaki ilişkiyi yönetir ve temel CRUD (Create, Read, Update, Delete) işlemlerini sağlar.

## 🚀 Features / Özellikler

*   **Pet Management / Evcil Hayvan Yönetimi:**
    *   Add new pet (POST `/api/pets`) / Yeni evcil hayvan ekleme
    *   List all pets (GET `/api/pets`) - **With Pagination & Sorting Support!** (`?page=`, `?size=`, `?sort=`) / Tüm evcil hayvanları listeleme - **Sayfalama ve Sıralama Desteğiyle!**
    *   Get a specific pet by ID (GET `/api/pets/{id}`) / Belirli bir evcil hayvanı ID ile getirme
    *   Update an existing pet (PUT `/api/pets/{id}`) / Mevcut bir evcil hayvanı güncelleme
    *   Delete a pet (DELETE `/api/pets/{id}`) / Bir evcil hayvanı silme
*   **Owner Management / Sahip Yönetimi:** (Note: API endpoints for Owner were not added within this project scope, but the database model and relationship with Pet are established.) / (Not: Bu proje kapsamında Owner için API endpoint'leri eklenmemiştir, ancak veritabanı modeli ve Pet ile ilişkisi kurulmuştur.)
    *   Owner entity and One-to-Many relationship definition with Pet. / Owner entity ve Pet ile One-to-Many ilişki tanımı.
    *   Association via `ownerId` when adding/updating a Pet. / Pet eklerken/güncellerken `ownerId` ile ilişkilendirme.
*   **Data Validation / Veri Doğrulama:** Incoming request data (`PetDto`) validity is checked (`@NotBlank`, `@Min`, etc.). / Gelen istek verilerinin (`PetDto`) geçerliliği kontrol edilir.
*   **Centralized Error Handling / Merkezi Hata Yönetimi:** API errors (e.g., 404 Not Found, 400 Bad Request) are caught using `@ControllerAdvice` and `@ExceptionHandler`, returning meaningful JSON responses. / API hataları yakalanır ve anlamlı JSON yanıtları döndürülür.
*   **DTO (Data Transfer Object) Usage / DTO Kullanımı:** DTOs are used for API requests and responses instead of Entity classes to control data structure and security. / API istek ve yanıtlarında Entity sınıfları yerine DTO'lar kullanılarak veri yapısı ve güvenlik kontrolü sağlanır.
*   **API Documentation (Swagger/OpenAPI) / API Dokümantasyonu:** Interactive API documentation accessible via `/swagger-ui.html`. / `/swagger-ui.html` adresi üzerinden erişilebilen interaktif API dokümantasyonu.
*   **Database / Veritabanı:** Uses H2 In-Memory database for development (`jdbc:h2:mem:testdb`). (Optional: Can be switched to MySQL). / Başlangıçta H2 In-Memory veritabanı kullanılır. (Opsiyonel: MySQL'e geçiş yapılabilir).

## 🛠️ Technologies Used / Kullanılan Teknolojiler

*   **Java:** 20 (or the version used in your project) / (veya projenizde kullandığınız sürüm)
*   **Spring Boot:** 3.2.7 (or the version used in your project) / (veya projenizde kullandığınız sürüm)
    *   Spring Web
    *   Spring Data JPA
    *   Spring Boot Actuator (optional / isteğe bağlı)
    *   Spring Boot DevTools (optional / isteğe bağlı)
    *   Spring Boot Starter Validation
    *   (Optional / Opsiyonel) Spring Boot Starter Security
*   **Database / Veritabanı:** H2 Database (For Development / Geliştirme için), (Optional: MySQL)
*   **ORM:** Hibernate (Used by Spring Data JPA / Spring Data JPA tarafından kullanılır)
*   **Build Tool / Build Aracı:** Maven
*   **Helper Libraries / Yardımcı Kütüphaneler:**
    *   Lombok (To reduce boilerplate code / Kod tekrarını azaltmak için)
    *   Springdoc OpenAPI (For Swagger UI / Swagger UI için)

## 🏁 Getting Started / Başlarken

### Prerequisites / Gereksinimler

*   Java JDK 17 or higher (Based on your project's `java.version`) / Java JDK 17 veya üzeri (Projenizin `java.version` özelliğine göre)
*   Apache Maven
*   (Optional / Opsiyonel) MySQL Server (If switched to MySQL / Eğer MySQL'e geçiş yapıldıysa)
*   (Optional / Opsiyonel) Git

### Installation and Running / Kurulum ve Çalıştırma

1.  **Clone the repository / Depoyu Klonlayın:**
    ```bash
    git clone https://github.com/YOUR_USERNAME/pettracker-springboot.git # Replace with your repository URL / URL'yi kendi deponuzla değiştirin
    cd pettracker-springboot
    ```

2.  **(Optional - If using MySQL) / (Opsiyonel - MySQL Kullanılıyorsa):** Update your MySQL connection details (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`) in the `src/main/resources/application.properties` file according to your local setup and create the corresponding database in MySQL. / `application.properties` dosyasındaki MySQL bağlantı bilgilerinizi kendi yerel ayarlarınıza göre güncelleyin ve MySQL'de ilgili veritabanını oluşturun.

3.  **Run the application using Maven / Uygulamayı Maven ile Çalıştırın:**
    ```bash
    mvn spring-boot:run
    ```
    The application will start on port `8098` by default (or the port specified in `application.properties`). / Uygulama varsayılan olarak `8098` portunda başlayacaktır (veya `application.properties` içinde belirttiğiniz portta).

### Access Points / Erişim Noktaları

*   **API Base URL:** `http://localhost:8098`
*   **Swagger UI (API Documentation / API Dokümantasyonu):** `http://localhost:8098/swagger-ui.html`
*   **H2 Database Console / H2 Veritabanı Konsolu:** `http://localhost:8098/h2-console`
    *   **JDBC URL:** `jdbc:h2:mem:testdb`
    *   **Username / Kullanıcı Adı:** `sa`
    *   **Password / Şifre:** (leave blank / boş bırakın)

## 📝 API Endpoints (Summary) / API Endpointleri (Özet)

All Pet endpoints are under `/api/pets`. Refer to Swagger UI for details. / Tüm Pet endpoint'leri `/api/pets` altında yer alır. Detaylar için Swagger UI'a bakınız.

*   `POST /api/pets`: Creates a new Pet (associated with an Owner ID). / Yeni bir Pet oluşturur (Owner ID'si ile birlikte).
*   `GET /api/pets`: Retrieves all Pets with pagination and sorting. / Tüm Pet'leri sayfalanmış ve sıralanmış olarak getirir.
*   `GET /api/pets/{id}`: Retrieves a specific Pet by its ID. / Belirtilen ID'ye sahip Pet'i getirir.
*   `PUT /api/pets/{id}`: Updates a specific Pet by its ID. / Belirtilen ID'ye sahip Pet'i günceller.
*   `DELETE /api/pets/{id}`: Deletes a specific Pet by its ID. / Belirtilen ID'ye sahip Pet'i siler.
