// Версия Jenkinsfile без использования Docker

pipeline {
    agent any

    tools {
        jdk 'JDK-17'
        maven 'Maven-3.9.8'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Забираем проект из Git..."
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                echo "Собираем проект и запускаем тесты..."
                sh 'mvn clean install'
            }
        }
    }

    // --- НАЧАЛО ИСПРАВЛЕННОГО БЛОКА ---
    post {
        always {
            echo "Сохраняем результаты тестов..."
            // 1. Собираем стандартный JUnit отчёт
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

            // 2. Публикуем красивый HTML-отчёт
            echo "Публикуем HTML-отчёт..."
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target',
                reportFiles: 'cucumber-html-report.html',
                reportName: 'Cucumber Report'
            ])

            // 3. Очищаем рабочее пространство
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
    // --- КОНЕЦ ИСПРАВЛЕННОГО БЛОКА ---
}