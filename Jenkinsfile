pipeline {

    agent any
        stages {
            stage('Test') {
                steps {
                    sh './gradlew test'
                }
            }
        }
        post {
            always {
                junit 'build/reports/*.xml'
            }
        }
}