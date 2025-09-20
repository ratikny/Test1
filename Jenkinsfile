// Версия Jenkinsfile без использования Docker

pipeline {
    // 1. ИЗМЕНЕНИЕ: Указываем Jenkins запускать задачу на любом свободном агенте,
    // а не в Docker-контейнере.
    agent any

    // 2. ИЗМЕНЕНИЕ: Раскомментировали и добавили инструменты.
    // Теперь Jenkins сам подготовит Java и Maven для нашей сборки.
    // Убедись, что названия 'JDK-11' и 'Maven-3.9.8' совпадают с теми,
    // что настроены в Jenkins -> Manage Jenkins -> Global Tool Configuration.
    tools {
        jdk 'JDK-17' // Добавь сюда JDK
        maven 'Maven-3.9.8'
    }

    // Этапы сборки остаются такими же, как у тебя.
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
                // Команда 'mvn clean install' скомпилирует код и запустит тесты.
                // Для Windows используй: bat 'mvn clean install'
                sh 'mvn clean install'
            }
        }
    }

    // Блок post тоже отличный, оставляем его без изменений.
    post {
        always {
            echo "Сохраняем результаты тестов..."
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