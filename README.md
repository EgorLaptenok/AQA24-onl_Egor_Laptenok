# AQA24-onl_Egor_Laptenok
# Выпускная работа
## Общий фреймворк автоматизации
Этот проект представляет собой общий фреймворк автоматизации, использующий Selenium, TestNG и Maven.

### Как использовать этот проект
- Склонируйте этот репозиторий на вашем локальном компьютере.
- Откройте проект в вашей предпочитаемой среде разработки.
- Обновите файл pom.xml с последними версиями зависимостей.
- Запустите тесты с помощью следующих команд:

#### Обновление зависимостей
Чтобы обновить все версии библиотек в проекте, используйте:
```
mvn versions:use-latest-versions
```

#### Запуск тестов
Для запуска всех тестов с использованием Maven, используйте:
```
mvn clean test -Dsuite=allTests -Dconfig=tests
```

Пример вывода:
```
Tests run: 34, Failures: 0, Errors: 0, Skipped: 0
```

#### Запуск конкретных тестов и методов

Например, для запуска только класса тестов api, используйте:
Название всех тестовых сценарий :: [api,regression,smoke,ui]
```
mvn clean test -Dsuite=api -Dconfig=tests
```

### Используемые библиотеки
- Selenium: фреймворк для автоматизации веб-приложений.
- TestNG: фреймворк для написания и выполнения автоматизированных тестов.
- Maven: инструмент для управления зависимостями и сборки проекта.
- Selenium: автоматизация веб-браузера.
- Log4j Core: логирование событий.
- Log4j: логирование сообщений.
- Lombok: упрощение кода.
- Gson: работа с JSON.
- Rest Assured: тестирование API.
- Allure: генерация отчетов.

### Презентация
Ссылка на презентацию: [https://docs.google.com/presentation/d/1N__rM81H2HEeVmErAjJLZchm8Z4lLPy79wyLzEk7LbA/edit?usp=sharing]
