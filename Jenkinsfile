pipeline {
    agent any
    tools {
        nodejs 'Nodejs-22.9.0'  // from the tools configuration
    }
    stages {
        stage("Init") {
            steps {
                script {
                    gv = load "script.groovy"  // Load external Groovy script
                }
            }
        }
        stage("Install Dependencies") {
            steps {
                script {
                    gv.buildNodeApp()  // Custom method to handle npm install
                }
            }
        }
        stage("Run Tests") {
            steps {
                script {
                    gv.runTests()  // Run your test function
                }
            }
        }
        stage("Build Image and Push to Docker Hub") {
            steps {
                script {
                    gv.buildImage()  // Build and push Docker image
                }
            }
        }
        stage("Deploy") {
            steps {
                script {
                    echo 'Deploying the Node.js application...'
                    gv.deployApp()  // Deploy the application
                }
            }
        }
    }
    post {
        always {
            // Publish JUnit test results
            junit '**/app/coverage/*.xml'  // Adjust path based on where jest-junit outputs XML
        }
        success {
            script {
                slackSend(channel: '#depi-slack-channel', message: "Build succeeded: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
            }
        }
        failure {
            script {
                slackSend(channel: '#depi-slack-channel', message: "Build failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
            }
        }
    }
}