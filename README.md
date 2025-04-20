# Zoo

Это Java-приложение для управления зоопарком. Система реализует REST API на Spring Boot и позволяет:

- управлять животными и вольерами
- организовывать кормление
- отслеживать состояние зоопарка
- просматривать статистику

## Стек технологий

- Java 17
- Spring Boot
- Maven
- Swagger UI
- JUnit 5
- In-memory Repositories

## Запуск проекта

```bash
mvn clean install
mvn spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui.html

## Основной функционал

| Возможность                                                              | Класс/модуль                                                                 |
|--------------------------------------------------------------------------|------------------------------------------------------------------------------|
| Добавить / удалить животное                                           | `AnimalController`, `ManageAnimalService`, `InMemoryAnimalRepository`       |
| Добавить / удалить вольер                                             | `EnclosureController`, `InMemoryEnclosureRepository`                        |
| Переместить животное между вольерами                                 | `EnclosureController`, `TransferRequest`, `Enclosure.removeAnimal/addAnimal`|
| Просмотр и управление расписанием кормления                          | `FeedingScheduleController`, `FeedingOrganizationService`                   |
| Выполнить кормление и обновить состояние животного                   | `FeedingOrganizationService`, `Animal.feed()`                               |
| Уборка вольера                                                        | `EnclosureController`, `Enclosure.clean()`                                  |
| Общая статистика зоопарка                                            | `ZooStatisticsController`, `ZooStatisticsService`                           |
| Обновление здоровья и ручное кормление                               | `AnimalController`, `Animal.setHealthStatus()`, `feed()`                    |

---

## Применение DDD и Clean Architecture

**Слои проекта:**

- `domain.model` — сущности: `Animal`, `Enclosure`, `FeedingSchedule`
- `domain.valueobject` — объекты-значения: `AnimalType`, `EnclosureType`, `Gender`, `HealthStatus`
- `application.service` — use cases: `FeedingOrganizationService`, `ManageAnimalService`, `AnimalTransferService`, `ZooStatisticsService`
- `application.port.in/out` — интерфейсы входа/выхода (`UseCase`, `Repository`)
- `infrastructure.repository` — InMemory реализации репозиториев
- `presentation.controller` — REST API: контроллеры

**Применённые концепции:**

| DDD Концепт        | Пример классов/модулей                                                 |
|--------------------|------------------------------------------------------------------------|
| Entity             | `Animal`, `Enclosure`, `FeedingSchedule`                               |
| Value Object       | `HealthStatus`, `Gender`, `AnimalType`, `EnclosureType`                |
| Repository         | `AnimalRepository`, `FeedingScheduleRepository`, `EnclosureRepository` |
| Application Service| `ManageAnimalService`, `FeedingOrganizationService`, ...               |

**Принципы Clean Architecture:**

- Зависимости направлены внутрь 
- Интерфейсы портов позволяют легко заменять инфраструктуру
- Чёткое разделение слоёв (`domain` ← `application` ← `infrastructure` ← `presentation`)

## Тесты

- Использованы `JUnit 5`
- Протестированы ключевые сущности:
  - `AnimalTest`, `EnclosureTest`, `FeedingScheduleTest`

```bash
mvn test
```

## Структура проекта

```
src/
├── main/java/com/zoo
│   ├── domain         # Модели: Animal, Enclosure, FeedingSchedule
│   ├── application    # Сервисы и use cases
│   ├── infrastructure # In-memory репозитории
│   ├── presentation   # REST контроллеры
│   └── ZooApplication.java
└── test/java/com/zoo
    └── ...            # Unit-тесты
```
