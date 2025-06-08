# Пояснительная записка к проекту "Zoo Management System"

## Назначение программы

Программа предназначена для автоматизации управления зоопарком. Система помогает персоналу зоопарка(смотрителям, ветеринарам, администраторам) контролировать состояние животных, управлять вольерами, организовывать кормление и получать статистику. Используется как локально, так и через веб-интерфейс.

## Функциональные требования

- Добавление, удаление, обновление информации о животных
- Создание и управление вольерами
- Назначение расписания кормления животных
- Отметка кормления как выполненного
- Перемещение животных между вольерами
- Уборка вольеров
- Просмотр текущей статистики по зоопарку (больные, голодные, всего животных)
- Обновление статуса здоровья животных
- Ручное кормление

## Нефункциональные требования

- REST API с авторизацией сотрудников
- Документация через Swagger UI
- Использование PostgreSQL как основной СУБД
- JPA и Spring Boot для взаимодействия с БД
- Возможность запуска в Docker-контейнере

## Предварительная схема базы данных

### Сущности:
- **Animal**
  - id (UUID, PK)
  - name (VARCHAR)
  - type (ENUM: CARNIVORE, HERBIVORE, ...)
  - birth_date (DATE)
  - gender (ENUM)
  - favorite_food (VARCHAR)
  - health_status (ENUM)
  - hungry (BOOLEAN)
  - last_fed_time (TIMESTAMP)
  - enclosure_id (UUID, FK → Enclosure.id)
    
- **Enclosure**
  - id (UUID, PK)
  - type (ENUM)
  - size (FLOAT)
  - max_animals (INTEGER)

- **FeedingSchedule**
  - id (UUID, PK)
  - animal_id (UUID, FK → Animal.id)
  - feeding_time (TIME)
  - food_type (VARCHAR)
  - completed (BOOLEAN)
    
### Связи:
- Enclosure 1:N Animal
- Animal 1:N FeedingSchedule

## Ограничения на данные 

- Каждое животное может находиться только в одном вольере
- Вольер может содержать не более `max_animals` животных
- Каждое кормление связано с одним животным
- Животное может иметь несколько записей в расписании кормления
- Кормление в расписании может быть выполнено только один раз

## Функциональные и многозначные зависимости

### Функциональные:
- `animal_id → name, birth_date, gender, health_status, hungry`
- `feeding_id → feeding_time, food_type, completed`

### Многозначные:

- `animal_id, feeding_time → food_type`

## Нормализация

Схема приведена к 3NF:
- Каждая таблица описывает одну сущность
- Все неключевые атрибуты зависят только от первичного ключа
- Нет транзитивных зависимостей

### Пример аномалии в недонормализованной схеме:

Если объединить `FeedingSchedule` и `Animal` в одну таблицу, то при каждом новом кормлении будет дублироваться информация о животном. Это приведёт к проблемам при обновлении (например, смена имени животного потребует изменения во всех строках расписания).

## SQL DDL 

```sql
CREATE TABLE enclosure (
  id UUID PRIMARY KEY,
  type VARCHAR(50),
  size FLOAT,
  max_animals INT
);

CREATE TABLE animal (
  id UUID PRIMARY KEY,
  name VARCHAR(100),
  type VARCHAR(50),
  birth_date DATE,
  gender VARCHAR(10),
  favorite_food VARCHAR(100),
  health_status VARCHAR(20),
  hungry BOOLEAN,
  last_fed_time TIMESTAMP,
  enclosure_id UUID REFERENCES enclosure(id)
);

CREATE TABLE feeding_schedule (
  id UUID PRIMARY KEY,
  animal_id UUID REFERENCES animal(id),
  feeding_time TIME,
  food_type VARCHAR(100),
  completed BOOLEAN
);
```

## SQL DML 

```sql
-- Добавить животное
INSERT INTO animal (id, name, type, birth_date, gender, favorite_food, health_status, hungry)
VALUES (gen_random_uuid(), 'Лев', 'CARNIVORE', '2017-05-01', 'MALE', 'мясо', 'HEALTHY', true);

-- Найти всех голодных животных
SELECT * FROM animal WHERE hungry = true;

-- Обновить статус кормления
UPDATE feeding_schedule SET completed = true WHERE id = '...';
```

## Транзакции 

```sql
BEGIN;

UPDATE feeding_schedule
SET completed = true
WHERE id = '...';

UPDATE animal
SET hungry = false, last_fed_time = NOW()
WHERE id = '...';

COMMIT;
```

## Пользовательский интерфейс

Интерфейс представлен Swagger UI по адресу:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Позволяет вызывать API: добавление животных, управление кормлениями, вольерами, статистикой и т.д.
