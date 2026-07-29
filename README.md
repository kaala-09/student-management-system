# 🎓 Student Management System

A full-stack **Student Management System** built using **Spring Boot, Spring MVC, Thymeleaf, Spring Data JPA, MySQL, and Bootstrap**. This application helps administrators efficiently manage students, courses, enrollments, and reports through a clean, responsive web interface.

## 📌 Features

### 🔐 Authentication
- Admin Login
- Secure Logout
- Spring Security Integration

### 📊 Dashboard
- Live database statistics
- Total Students
- Total Courses
- Total Enrollments
- Total Reports
- Recent Students
- Quick Action Buttons

### 👨‍🎓 Student Management
- Add Student
- View Students
- Edit Student
- Delete Student
- Search Students
- Admission Number
- Department
- Academic Year
- Contact Details

### 📚 Course Management
- Add Course
- View Courses
- Edit Course
- Delete Course
- Search Courses
- Course Fee
- Amount Paid
- Remaining Balance (Auto Calculated)

### 📝 Enrollment Management
- Enroll Students
- View Enrollments
- Edit Enrollment
- Delete Enrollment
- Search Enrollment

### 📈 Reports
- View Reports
- Generate Reports
- Dashboard Statistics

### ⚙️ Settings
- Admin Profile
- Change Password
- Logout

---

# 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL
- Bootstrap 5
- HTML5
- CSS3
- JavaScript
- Maven

---

# 📂 Project Structure

```
student-management-system
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── StudentManagementApplication.java
│   │   │
│   │   └── resources
│   │       ├── static
│   │       │   ├── css
│   │       │   ├── js
│   │       │   └── images
│   │       ├── templates
│   │       └── application.properties
│
└── pom.xml
```

---

# 💻 Database

Database Name

```
student_management
```

Update the following in **application.properties**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# 🚀 Installation

### Clone Repository

```bash
git clone https://github.com/kaala-09/student-management-system.git
```

### Open Project

Import into:

- IntelliJ IDEA
- Eclipse
- VS Code

---

### Create Database

```sql
CREATE DATABASE student_management;
```

---

### Run Application

Using Maven

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

Application URL

```
http://localhost:8082
```

---

# 🔑 Default Admin Login

```
Username : admin

Password : admin123
```

---

# 📸 Screens

- Login Page
- Dashboard
- Student Management
- Course Management
- Enrollment Management
- Reports
- Responsive Sidebar
- Search Functionality

> Add screenshots inside a `screenshots/` folder and reference them here.

Example:

```md
## Dashboard

![Dashboard](screenshots/dashboard.png)

## Students

![Students](screenshots/students.png)
```

---

# 🔍 Search Features

Search functionality is available for

- Students
- Courses
- Enrollments
- Reports

---

# 📈 Dashboard Widgets

✔ Total Students

✔ Total Courses

✔ Total Enrollments

✔ Total Reports

✔ Recent Students

✔ Quick Actions

---

# 🎯 Future Improvements

- Student Profile Photos
- Export PDF Reports
- Excel Export
- Email Notifications
- Role-based Authentication
- Attendance Module
- Fee Management
- Dark Mode
- REST API
- Docker Deployment

---

# 🤝 Contributing

Contributions are welcome.

1. Fork the repository

2. Create a feature branch

```bash
git checkout -b feature-name
```

3. Commit your changes

```bash
git commit -m "Added new feature"
```

4. Push to GitHub

```bash
git push origin feature-name
```

5. Create a Pull Request

---

# 👨‍💻 Author

**Bharathkumar Kaala**

GitHub:

:contentReference[oaicite:0]{index=0}

Repository:

:contentReference[oaicite:1]{index=1}

---

# 📄 License

This project is licensed under the MIT License.

---

⭐ If you found this project helpful, please consider giving it a Star on GitHub!
