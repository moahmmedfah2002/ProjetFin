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
                git branch: 'master',
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
                    echo 'Tentative d\'envoi de mail...'

                                    emailext body: """
                                        <p>Statut du Build: <b>${currentBuild.result ?: 'SUCCESS'}</b></p>
                                        <p>Lien: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                                    """,
                                    subject: "Build Jenkins: ${env.JOB_NAME} - ${currentBuild.result ?: 'SUCCESS'}",
                                    to: 'fahlaouimohammed@gmail.com', // REMPLACEZ CECI PAR VOTRE EMAIL
                                    mimeType: 'text/html'
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