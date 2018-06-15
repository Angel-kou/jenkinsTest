pipeline {

    agent any
        stages {
            stage('Test') {
                steps {
                    sh 'make check'
                }
            }
        }
        post {
            always {
                junit 'build/reports/*.xml'
            }
        }
}