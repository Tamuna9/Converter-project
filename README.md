# Converter-project
Web application for currency conversion
Technologies:
- Entry point (Main class MyConverterProjectApplication method main() start tomcat, listening on: http://localhost:8080)
- Backend: Spring Boot, Hibernate, MariaDB
- Frontend: JSP, JavaScript, CSS
- API: CurrencyFreaks
- Programming languages
  
Features:
✅ User registration & authentication
✅ Currency conversion
✅ Saving conversion history
✅ Exporting data to CSV
✅ Auto DB initialization

Architecture:
**Backend:**
- Controllers(REST API)
- Services(Business logic)
- Repositories(Data access)

**Frontend:**
- script.js
- jsp
- style.css

Database structure:
Users:
id
Username
password
email

codesCurrency:
currency_iso_code
currency_name
rate

Conversion history:
user_id
conversion_id
operation_date
source_amount
source_currency
target_amount
target_currency

System Workflow:
1. User registers/logs in
2. Selects currency & converts
3. Result is displayed & saved
4. History is available
5. Exports CSV data

Conclusion:
✅ Automates conversion & history tracking
✅ Uses modern tech & real-time exchange rates



