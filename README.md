This is a sample spring boot project to integrate ci cd pipeline with jenkins and docker
```

### 2. Create a Dockerfile
```Dockerfile: Dockerfile
FROM openjdk:17-jdk-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### 3. Create a Jenkinsfile
```Jenkinsfile: Jenkinsfile
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    sh "${mvnHome}/bin/mvn clean package"
                }
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    docker.build("spring-boot-app")
                }
            }
        }
        stage('Docker Push') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        docker.image("spring-boot-app").push("latest")
                    }
                }
            }
        }
    }
}
```

### 4. Create a Docker Hub Credentials
- Go to Jenkins > Manage Jenkins > Manage Credentials
- Click on 'Global credentials (unrestricted)' > Add Credentials
- Choose 'Username with password' as the kind
- Enter your Docker Hub username and password
- Set the ID to 'docker-hub-credentials'
- Click OK
- Click Save