pipeline {
    // Указываем Jenkins запускать все шаги внутри специального Docker-контейнера,
    // в котором уже есть Java и все зависимости для браузеров.
    agent {
        docker {
            image 'mcr.microsoft.com/playwright/java:v1.42.0-jammy'
        }
    }

    // Инструменты нам больше не нужны, так как Maven уже есть в Docker-образе.
    // tools {
    //     maven 'Maven-3.9.8'
    // }

    stages {
        // Этап 1: Скачивание кода из репозитория
        stage('Checkout') {
            steps {
                echo "Забираем проект из Git..."
                checkout scm
            }
        }

        // Этап 2: Сборка и запуск тестов
        stage('Build & Test') {
            steps {
                echo "Собираем проект и запускаем тесты..."
                // Команда 'mvn clean install'
                sh 'mvn clean install'
            }
        }
    }

    // Блок, который выполняется после всех этапов
    post {
        always {
            echo "Сохраняем результаты тестов..."
            // Собираем отчёты о тестах для отображения в интерфейсе Jenkins
            // allowEmptyResults = true нужно, чтобы сборка не падала, если тесты не запустились
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

            echo "Убираем временные файлы..."
            cleanWs()
        }
        success {
            echo "Все шаги прошли успешно!"
        }
        failure {
            echo "Ошибка! Проверь логи."
        }
    }
}
