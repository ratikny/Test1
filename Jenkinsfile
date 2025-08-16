pipeline {
    agent any

    tools {
        maven 'Maven'   // имя Maven из Jenkins (Global Tool Config)
        jdk 'JDK11'     // JDK из Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                echo " Забираем проект из Git..."
                git branch: 'main', url: 'https://github.com/username/your-project.git'
            }
        }

        stage('Build') {
            steps {
                echo "🔨 Собираем проект..."
                sh "mvn clean compile -DskipTests"
            }
        }

        stage('Run Tests') {
            steps {
                echo " Запускаем Cucumber + TestNG тесты..."
                sh "mvn test"
            }
        }

        stage('Allure Report') {
            steps {
                echo " Генерируем Allure отчёт..."
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }

        stage('Archive Results') {
            steps {
                echo " Сохраняем результаты тестов..."
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo " Убираем временные файлы..."
            cleanWs()
        }
        success {
            echo " Все шаги прошли успешно!"
        }
        failure {
            echo " Ошибка! Проверь логи."
        }
    }
}
