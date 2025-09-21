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

    post {
        always {
            echo "Сохраняем результаты тестов..."
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

            // Диагностическая команда для проверки содержимого папки target
            echo "Проверяем содержимое папки target..."
            sh 'ls -l target'

            echo "Публикуем HTML-отчёт..."
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target',
                reportFiles: 'cucumber-html-report.html',
                reportName: 'Cucumber Report'
            ])

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