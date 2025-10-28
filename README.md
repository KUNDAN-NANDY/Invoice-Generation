# 🧾 Invoice Generation System

An intelligent **Spring Boot** application that generates professional **PDF invoices** using **JasperReports**, stores them in **MySQL**, and provides easy APIs for retrieval and download.  

Built with a clean **3-layer architecture (Controller → Service → Repository)** and a focus on scalability, modularity, and performance.  

---

## 🚀 Features

✅ Generate invoices dynamically from JSON data  
✅ Export invoices as **PDF** using JasperReports  
✅ Automatically store generated PDFs in **MySQL** (as BLOBs)  
✅ Retrieve invoice details or PDF directly through API  
✅ Follows clean and maintainable architecture  

---

## 🧩 Tech Stack

| Layer | Technology |
|--------|-------------|
| **Backend Framework** | Spring Boot |
| **Database** | MySQL |
| **Reporting Engine** | JasperReports |
| **ORM** | Spring Data JPA (Hibernate) |
| **Language** | Java 21 |
| **Build Tool** | Maven |
| **Helper Libraries** | Lombok |
| **API Testing** | Postman |

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/kundannandy/jasperreport/invoicegeneration/
│   │   ├── controller/ → Handles API endpoints
│   │   ├── service/ → Business logic for invoice generation
│   │   ├── repository/ → Database operations (JPA)
│   │   ├── entity/ → JPA Entities
│   │   ├── dto/ → Request objects
│   │   └── model/ → Domain models (Invoice items)
│   └── resources/
│       ├── templates/invoiceTemplate.jrxml → JasperReports template
│       └── application.properties → DB & app config
└── test/ → Unit tests
```

---

## 🧠 System Workflow

1️⃣ **User** sends a POST request with customer & item data  
2️⃣ **Controller** passes it to the Service layer  
3️⃣ **Service**:
   - Generates PDF invoice using JasperReports  
   - Calculates total  
   - Stores the PDF in MySQL (as byte array)  
4️⃣ **Repository** handles DB operations  
5️⃣ **API** returns PDF as response or saves record for later download  

---

## 🧮 Example API Endpoints

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `/invoice/generate` | Generate a new invoice and return PDF |
| `GET` | `/invoice/{id}` | Fetch invoice details by ID |
| `GET` | `/invoice/pdf/{id}` | Download invoice as PDF |

---

### 🧾 Example Request

**POST → `/invoice/generate`**

```json
{
  "customerName": "Kundan Nandy",
  "customerPhone": "9876543210",
  "items": [
    { "itemName": "Bluetooth Headset", "quantity": 2, "itemType": "Electronics", "price": 1599.99 },
    { "itemName": "USB-C Cable", "quantity": 1, "itemType": "Accessory", "price": 499.00 }
  ]
}
```

---

### 🧾 Example Response (JSON)

```json
{
  "id": 1,
  "customerName": "Kundan Nandy",
  "customerPhone": "9876543210",
  "createdAt": "2025-10-25T14:52:00",
  "pdfData": "Base64EncodedPDFBytes"
}
```

---

## 🗄️ Database Schema

**Table:** `invoice_records`

| Column | Type | Description |
|---------|------|-------------|
| `id` | BIGINT | Primary Key |
| `customer_name` | VARCHAR | Customer’s Name |
| `customer_phone` | VARCHAR | Customer’s Contact |
| `pdf_data` | LONGBLOB | Stores generated PDF bytes |
| `created_at` | DATETIME | Timestamp |

---

## 🧰 How to Run Locally

1️⃣ **Clone Repository**
```bash
git clone https://github.com/<your-username>/invoicegeneration.git
cd invoicegeneration
```

2️⃣ **Configure Database**
Create a MySQL database:
```sql
CREATE DATABASE invoice_db;
```

3️⃣ **Update `application.properties`**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/invoice_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4️⃣ **Build and Run**

5️⃣ **Test APIs in Postman**

---

## 🧩 Design Highlights

- ✅ **3-layer architecture:** Controller, Service, Repository  
- ✅ **Dependency Injection:** via `@Autowired`  
- ✅ **ORM Mapping:** via JPA `@Entity`  
- ✅ **Report Generation:** via JasperReports templates  
- ✅ **Binary Data Handling:** returns `ResponseEntity<byte[]>`  

---

## 💡 Future Enhancements

- Add email service to send invoice PDFs automatically  
- Store PDFs in AWS S3 instead of database  
- Add user authentication (JWT-based)  
- Integrate frontend UI for uploading orders and viewing invoices  

---

## 👨‍💻 Author

**Kundan Nandy**  
💼 Software Analyst @ Capgemini India  
💡 Passionate about Java | Spring Boot | Microservices | AI Integration  
📧 [kundannandy27598@gmail.com](mailto:kundannandy27598@gmail.com)  
🌐 www.linkedin.com/in/kundan-nandy

---

## 🏁 Conclusion

> This project demonstrates a **real-world business workflow** integrating **Spring Boot**, **JasperReports**, and **MySQL** — showing your ability to design scalable backend systems, manage data efficiently, and generate dynamic reports.

---

⭐ **If you like this project, give it a star on GitHub!**
