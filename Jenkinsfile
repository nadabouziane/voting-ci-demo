pipeline {
    agent any

    tools {
        maven 'Maven-3.9.11'
        jdk 'JDK17'
    }

    environment {
        SONAR_PROJECT_KEY = 'voting-ci-demo'
        MAVEN_OPTS = '-Xmx1024m -XX:+UseG1GC'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Tests') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('JUnit Reports') {
            steps {
                junit allowEmptyResults: false,
                      testResults: 'target/surefire-reports/*.xml'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh """
                        mvn sonar:sonar \
                          -Dsonar.projectKey=${SONAR_PROJECT_KEY}
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Publish JaCoCo HTML') {
            steps {
                publishHTML(target: [
                    reportName: 'JaCoCo Coverage Report',
                    reportDir: 'target/site/jacoco',
                    reportFiles: 'index.html',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        success {
            echo '✅ Jenkins environment fully aligned with POM'
        }
        failure {
            echo '❌ Build failed due to Jenkins environment mismatch'
        }
    }
}
