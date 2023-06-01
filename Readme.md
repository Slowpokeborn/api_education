Проект для изучения автоматизации api с помощью сайта https://reqres.in

Запуск тестовых suites в консоли (cmd) (наборов тестов)

1 Suite по умолчанию - все тесты (AllTestSuite)

    mvn clean test allure:serve

2 Запуск конкретного suite:

    mvn clean test -Dsuite=UsersPositiveSuite allure:serve

    mvn clean test -Dsuite=UsersSuite allure:serve




