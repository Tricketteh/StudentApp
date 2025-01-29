# StudentApp

REST сервис получения информации о студентах с авторизацией через  Google OAuth2. 

## Установка
1. Убедитесь, что установлен MongoDB и он работает на `localhost:27017`.
2. Склонируйте репозиторий.
3. В файле application.conf заменить поля на ваши clientId и clientSecret
4. Соберите проект: `sbt run`.

## Примеры запросов

### Получение всех студентов
```bash
curl -X GET http://localhost:9000/students
```

### Получение студента по ID
```bash
curl -X GET http://localhost:9000/students/{id}
```

### Добавление студента
```bash
curl -X POST http://localhost:9000/students -H "Content-Type: application/json" -d '{"firstName":"Иван","lastName":"Иванов","middleName":"Иванович","group":"101","averageGrade":4.5}'
```

### Обновление студента
```bash
curl -X PUT http://localhost:9000/students/{id} -H "Content-Type: application/json" -d '{"firstName":"Петр","lastName":"Петров","middleName":"Петрович","group":"102","averageGrade":3.8}'
```

### Удаление студента
```bash
curl -X DELETE http://localhost:9000/students/{id}
