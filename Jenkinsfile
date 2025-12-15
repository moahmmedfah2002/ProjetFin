pipeline {
    agent any
    

    tools {
        maven 'Maven_3.8'
        jdk 'JDK_11'
    }

    environment {
        JAR_NAME = 'spaghetti-finance-1.0.0.jar'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/moahmmedfah2002/ProjetFin.git'
            }
        }

        stage('Clean & Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }



        stage('Packaging') {
            steps {
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
    }

    post {
        always {

            script {
                try {
                    emailext attachLog: true,
                        subject: "Build ${currentBuild.result}: ${JOB_NAME} #${BUILD_NUMBER}",
                        to: 'fahlaouimohammed@gmail.com',
                        body: "Résultat du build : ${currentBuild.result}\nLien : ${env.BUILD_URL}"
                } catch (Exception e) {
                    echo "L'envoi d'email a échoué (Probablement pas de config SMTP). Le build reste valide."
                }
            }
        }
        failure {
            echo "Le build a échoué. Vérifiez les logs."
        }
        success {
            echo "Le build a réussi ! L'artefact est prêt."
        }
    }
}