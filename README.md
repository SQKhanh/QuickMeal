# 🍔 QuickMeal – Hệ thống đặt món & quản trị F&B

QuickMeal là một hệ thống thương mại điện tử chuyên biệt cho lĩnh vực **F&B (Food & Beverage)**, được xây dựng trong khuôn khổ **bài tập lớn môn học**.  
Hệ thống tập trung vào việc tối ưu hóa luồng đặt món, quản trị vận hành và khả năng xử lý đồng thời phía Backend.

---

## 🎯 Mục tiêu dự án

- Xây dựng hệ thống **đặt món trực tuyến** cho khách hàng
- Cung cấp **Dashboard quản trị** cho Admin và Staff
- Áp dụng tư duy **Backend hiện đại** với Java 21 & Virtual Threads
- Thiết kế kiến trúc rõ ràng, dễ mở rộng và sát với thực tế

---

## 🧩 Chức năng chính

### 👤 Khách hàng (Customer)

- Xem danh sách món ăn
- Thêm món vào giỏ hàng
- Đặt hàng (Checkout)
- Theo dõi trạng thái đơn hàng

### 🧑‍🍳 Nhân viên (Staff)

- Tiếp nhận và xử lý đơn hàng
- Cập nhật trạng thái đơn (Preparing / Completed)
- Quản lý thực đơn trong phạm vi phân quyền

### 🛠️ Quản trị viên (Admin)

- Quản lý người dùng
- Quản lý danh mục và sản phẩm
- Theo dõi Dashboard thống kê (mock data)
- Phân quyền hệ thống

---

## 🏗️ Kiến trúc hệ thống

### Backend

- **Java 21**
- **Spring Boot 3**
- Kiến trúc phân tầng:
  - Controller Layer
  - Service Layer
  - Repository Layer
- **Virtual Threads** cho xử lý I/O-bound
- **JWT Stateless Authentication** + Token Whitelist
- **HikariCP** cho quản lý kết nối Database

### Frontend

- **React**
- **Shadcn/UI**
- **Tailwind CSS**
- Component-based Architecture
- Dashboard quản trị và giao diện khách hàng tách biệt

---

## 🔐 Bảo mật

- Xác thực bằng **JWT (Stateless)**
- Custom `JWT Filter Chain`
- Cơ chế **Whitelist Token** để vô hiệu hóa token khi logout
- Phân quyền dựa trên Role:
  - `ADMIN`
  - `STAFF`
  - `CUSTOMER`

---

## 📊 Dashboard & Dữ liệu

- Dashboard quản trị cho Admin & Staff
- Biểu đồ doanh thu và đơn hàng **sử dụng mock data**
- Mục tiêu:
  - Hoàn thiện giao diện
  - Mô phỏng luồng dữ liệu
  - Dễ dàng thay thế bằng dữ liệu thật trong tương lai

---

## 🖼️ Quản lý hình ảnh

- Lưu trữ hình ảnh sản phẩm trên **File System**
- Không lưu BLOB trong Database
- Cấu trúc thư mục:

```
/uploads/{category_name}/{product_id}.jpg
```

- Tự động dọn dẹp file khi cập nhật / xóa sản phẩm

---

## ⚙️ Công nghệ sử dụng

| Thành phần | Công nghệ                      |
| ---------- | ------------------------------ |
| Backend    | Java 21, Spring Boot 3         |
| Frontend   | React, Shadcn/UI, Tailwind CSS |
| Database   | MySQL                          |
| Security   | JWT, Spring Security           |
| Threading  | Virtual Threads                |
| Build Tool | Gradle                         |

---

## 🚧 Phạm vi & Giới hạn

- Dashboard hiện tại sử dụng **mock data**
- Chưa triển khai:
- CI/CD
- Monitoring
- Analytics thực tế
- Phù hợp với phạm vi bài tập lớn và nhóm nhỏ (2 người)

---

## 🔮 Định hướng phát triển

- Thay thế mock data bằng thống kê thực tế
- Tích hợp Redis cache cho Dashboard
- Mở rộng theo hướng Cloud Native / Microservices
- Nâng cao bảo mật (OAuth2, MFA)

---

## 👨‍💻 Nhóm thực hiện

- **Khánh Dzai**

  - Chịu trách nhiệm chính trong phát triển hệ thống
  - Thiết kế kiến trúc Backend, Frontend và bảo mật
  - Triển khai Virtual Threads, JWT, Dashboard quản trị

- **Thành viên nhóm**
  - Tham gia hỗ trợ một số hạng mục theo phân công

**Giảng viên hướng dẫn:** ThS. Hoàng Quang Huy

---

## 📄 Ghi chú

Dự án được xây dựng với mục tiêu học tập, nghiên cứu và áp dụng kiến thức lập trình Web, Backend và kiến trúc phần mềm vào một bài toán thực tế trong lĩnh vực F&B.
