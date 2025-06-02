# Zoo Management System

Java-приложение на Spring Boot для управления зоопарком с использованием PostgreSQL и JPA. Система предоставляет REST API для управления животными, вольерами, расписанием кормлений и сбора статистики.

## Стек технологий

- Java 17  
- Spring Boot  
- Spring Data JPA  
- PostgreSQL  
- Maven  
- Swagger UI  

## Запуск проекта

```bash
mvn clean install
docker run --name zoo-postgres -e POSTGRES_DB=zoo -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
mvn spring-boot:run
```

Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 📊 Основной функционал

| Возможность                                      | Классы / Компоненты                                             |
|--------------------------------------------------|-----------------------------------------------------------------|
| Добавление / удаление животных и вольеров        | `AnimalController`, `EnclosureController`, `AnimalRepository`   |
| Кормление, расписание, ручное кормление          | `FeedingScheduleController`, `FeedingOrganizationService`       |
| Статистика зоопарка                              | `ZooStatisticsController`, `ZooStatisticsService`               |
| Перемещение животных между вольерами             | `EnclosureController`, `AnimalTransferService`                  |
| Уборка вольеров                                   | `Enclosure.clean()`                                             |
| Изменение состояния здоровья                     | `Animal.setHealthStatus()`                                      |

---

## 🧠 Архитектура (DDD + Clean Architecture)

| Слой                | Описание                                                                  |
|---------------------|---------------------------------------------------------------------------|
| `domain.model`      | Модели: `Animal`, `Enclosure`, `FeedingSchedule`                          |
| `domain.valueobject`| Объекты-значения: `Gender`, `AnimalType`, `HealthStatus`, `EnclosureType`|
| `application.service` | Сервисы: `FeedingOrganizationService`, `ManageAnimalService`, `ZooStatisticsService` |
| `application.port.in/out` | Интерфейсы use-case и репозиториев                                  |
| `infrastructure.repository` | Репозитории через Spring Data JPA                                 |
| `presentation.controller`   | REST API-контроллеры                                              |

## Структура проекта

```
src/
├── main/java/com/zoo
    ├── domain         # Сущности и объекты-значения (Entity, Value Object)
    ├── application    # Сервисы и use cases
    ├── infrastructure # Реализация репозиториев через JPA
    ├── presentation   # REST контроллеры
    └── ZooApplication.java
```

## Как пользоваться веб-приложением

После запуска проекта перейдите в Swagger UI:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### 1. Добавить животное

- Перейти в `animal-controller` : `POST/animals`
- Вставить JSON:

```json
{
  "id": "11111111-1111-1111-1111-111111111111",
  "name": "Лев",
  "type": "CARNIVORE",
  "birthDate": "2017-05-01",
  "gender": "MALE",
  "favoriteFood": "мясо",
  "healthStatus": "HEALTHY",
  "hungry": true
}
```

### 2. Добавить вольер

- Перейти в `enclosure-controller` : `POST/enclosures`
- Вставить JSON:

```json
{
  "id": "22222222-2222-2222-2222-222222222222",
  "type": "CARNIVORE",
  "maxAnimals": 3,
  "size": 100.0
}
```

### 3. Поместить животное в вольер

- Перейти в `POST/enclosures/{id}/add-animal`
- Вставить ID вольера в путь 
- В теле запроса передать ID животного как строку:

```json
"11111111-1111-1111-1111-111111111111"
```

### 4. Добавить расписание кормления

- Перейти в `feeding-schedule-controller` : `POST/feeding-schedule`
- Пример запроса:

```json
{
  "animalId": "11111111-1111-1111-1111-111111111111",
  "feedingTime": "12:30",
  "foodType": "мясо"
}
```

### 5. Отметить кормление как выполненное

- Перейти в `POST/feeding-schedule/{id}/complete`
- Вставить `id` из расписания

### 6. Посмотреть статистику

- Перейти в `GET/statistics`
- Увидите:
  - Общее количество животных
  - Голодные и больные
  - Статистика по типам

---

### Дополнительно:

- `PATCH/animals/{id}/health` — изменить статус здоровья
- `POST/animals/{id}/feed` — ручное кормление
- `POST/enclosures/{id}/clean` — уборка вольера
- `POST/enclosures/transfer` — переместить животное


## 🛠️ Конфигурация подключения к PostgreSQL

В `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/zoo
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```