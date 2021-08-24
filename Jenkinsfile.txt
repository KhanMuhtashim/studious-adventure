pipeline {
    agent any
    tools {
        maven "Maven"
    }
    environment {
        NEXUS_VERSION = "nexus3.33"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "http://54.83.73.70:8081"
        NEXUS_REPOSITORY = "simpleapp-release"
        NEXUS_CREDENTIAL_ID = "1314288f-e205-445b-a56c-dce2cf3f28dd"
    }
    stages {
        stage("Clone code from VCS") {
            steps {
                withCredentials([string(credentialsId: 'Git', variable: 'SECRET')]) {
                    git 'https://github.com/KhanMuhtashim/studious-adventure.git'
                }
            }
        }
        stage("Maven Build") {
            steps {
                script {
                    sh 'mvn -version'
                }
            }
        }
        stage("Deployment") {
            steps {
                script {
                    nexusArtifactUploader artifacts: [
                        [
                            artifactId: 'my-app', 
                            classifier: '', 
                            file: 'target/my-app-1.0.0.war', 
                            type: 'war'
                        ]
                    ], 
                    credentialsId: '1314288f-e205-445b-a56c-dce2cf3f28dd', 
                    groupId: 'com.mycompany.app', 
                    nexusUrl: 'http://54.83.73.70:8081', 
                    nexusVersion: 'nexus3', 
                    protocol: 'http', 
                    repository: 'http://54.83.73.70:8081/repository/simpleapp-release/', 
                    version: '1.0.0'
                }
            }
        }
    }
}