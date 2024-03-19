pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Run tests') {
            steps {
                bat 'mvn clean test -Dsuite=%SUITE% -Dconfig=%CONFIG%'
            }
        }
        stage('Copy logs') {
            steps {
                archiveArtifacts artifacts: 'target/logs/*', followSymlinks: false
            }
        }
    }
    post('Allure result'){
        always{
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}