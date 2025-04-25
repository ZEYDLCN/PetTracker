# PetTracker - Spring Boot Projesi

Bu proje, Spring Boot kullanarak evcil hayvan bilgilerini yönetmek için geliştirilmiş basit bir RESTful API uygulamasıdır. Kullanıcılar (sahipler) ve onların evcil hayvanları arasındaki ilişkiyi yönetir ve temel CRUD (Create, Read, Update, Delete) işlemlerini sağlar.


## 🚀 Özellikler

*   **Evcil Hayvan (Pet) Yönetimi:**
    *   Yeni evcil hayvan ekleme (POST `/api/pets`)
    *   Tüm evcil hayvanları listeleme (GET `/api/pets`) - **Sayfalama ve Sıralama Desteğiyle!** (`?page=`, `?size=`, `?sort=`)
    *   Belirli bir evcil hayvanı ID ile getirme (GET `/api/pets/{id}`)
    *   Mevcut bir evcil hayvanı güncelleme (PUT `/api/pets/{id}`)
    *   Bir evcil hayvanı silme (DELETE `/api/pets/{id}`)
*   **Sahip (Owner) Yönetimi:** (Not: Bu proje kapsamında Owner için API endpoint'leri eklenmemiştir, ancak veritabanı modeli ve Pet ile ilişkisi kurulmuştur.)
    *   Owner entity ve Pet ile One-to-Many ilişki tanımı.
    *   Pet eklerken/güncellerken `ownerId` ile ilişkilendirme.
*   **Veri Doğrulama (Validation):** Gelen istek verilerinin (`PetDto`) geçerliliği kontrol edilir (`@NotBlank`, `@Min` vb.).
*   **Merkezi Hata Yönetimi (Error Handling):** `@ControllerAdvice` ve `@ExceptionHandler` kullanılarak API hataları (örn: 404 Not Found, 400 Bad Request) yakalanır ve anlamlı JSON yanıtları döndürülür.
*   **DTO (Data Transfer Object) Kullanımı:** API istek ve yanıtlarında Entity sınıfları yerine DTO'lar kullanılarak veri yapısı ve güvenlik kontrolü sağlanır.
*   **API Dokümantasyonu (Swagger/OpenAPI):** `/swagger-ui.html` adresi üzerinden erişilebilen interaktif API dokümantasyonu.
*   **Veritabanı:** Başlangıçta H2 In-Memory veritabanı kullanılır (`jdbc:h2:mem:testdb`).

## 🛠️ Kullanılan Teknolojiler

*   **Java:** 20 (veya projenizde kullandığınız sürüm)
*   **Spring Boot:** 3.2.7 (veya projenizde kullandığınız sürüm)
    *   Spring Web
    *   Spring Data JPA
    *   Spring Boot Actuator (isteğe bağlı)
    *   Spring Boot DevTools (isteğe bağlı)
    *   Spring Boot Starter Validation
    *   (Opsiyonel) Spring Boot Starter Security
*   **Veritabanı:** H2 Database (Geliştirme için), (Opsiyonel: MySQL'e geçiş yapılabilir)
*   **ORM:** Hibernate (Spring Data JPA tarafından kullanılır)
*   **Build Aracı:** Maven
*   **Yardımcı Kütüphaneler:**
    *   Lombok (Kod tekrarını azaltmak için)
    *   Springdoc OpenAPI (Swagger UI için)

## 🏁 Başlarken

### Gereksinimler

*   Java JDK 17 veya üzeri (Projenizin `java.version` özelliğine göre)
*   Apache Maven
*   (Opsiyonel) MySQL Server (Eğer MySQL'e geçiş yapıldıysa)
*   (Opsiyonel) Git

### Kurulum ve Çalıştırma

1.  **Depoyu Klonlayın:**
    ```bash
    git clone https://github.com/KULLANICI_ADINIZ/pettracker-springboot.git # URL'yi kendi deponuzla değiştirin
    cd pettracker-springboot
    ```

2.  **(Opsiyonel - MySQL Kullanılıyorsa):** `src/main/resources/application.properties` dosyasındaki MySQL bağlantı bilgilerinizi (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`) kendi yerel ayarlarınıza göre güncelleyin ve MySQL'de ilgili veritabanını oluşturun.

3.  **Uygulamayı Maven ile Çalıştırın:**
    ```bash
    mvn spring-boot:run
    ```
    Uygulama varsayılan olarak `8098` portunda başlayacaktır (veya `application.properties` içinde belirttiğiniz portta).

### Erişim Noktaları

*   **API Base URL:** `http://localhost:8098`
*   **Swagger UI (API Dokümantasyonu):** `http://localhost:8098/swagger-ui.html`
*   **H2 Veritabanı Konsolu:** `http://localhost:8098/h2-console`
    *   **JDBC URL:** `jdbc:h2:mem:testdb`
    *   **Kullanıcı Adı:** `sa`
    *   **Şifre:** (boş bırakın)

## 📝 API Endpointleri (Özet)

Tüm Pet endpoint'leri `/api/pets` altında yer alır. Detaylar için Swagger UI'a bakınız.

*   `POST /api/pets`: Yeni bir Pet oluşturur (Owner ID'si ile birlikte).
*   `GET /api/pets`: Tüm Pet'leri sayfalanmış ve sıralanmış olarak getirir.
*   `GET /api/pets/{id}`: Belirtilen ID'ye sahip Pet'i getirir.
*   `PUT /api/pets/{id}`: Belirtilen ID'ye sahip Pet'i günceller.
*   `DELETE /api/pets/{id}`: Belirtilen ID'ye sahip Pet'i siler.

---
