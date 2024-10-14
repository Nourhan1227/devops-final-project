def buildNodeApp() {
    echo "Building Node.js application..."
    dir('app') {
        sh 'npm install'
    }
}

def runTests() {
    echo "Running tests..."
    dir('app') {
        sh 'npm test'  // This will generate the JUnit-compatible XML report
    }
}

def buildImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'Dockeraut', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t ghanemovic/depi-final-project:latest ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push ghanemovic/depi-final-project:latest"
    }
}

def deployApp() {
    echo "Deploying the application to EKS..."

    // Use AWS credentials
    // withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'jenkins-aws-access']]) {
        // Uncomment if you have a kubeconfig file
        // withCredentials([file(credentialsId: 'your-kubeconfig-id', variable: 'KUBECONFIG_FILE')]) {
        //     // Set the KUBECONFIG environment variable
        //     sh 'export KUBECONFIG=$KUBECONFIG_FILE'

        // Change to the directory containing your deployment and service files
        // dir('/home/nour/depi/Final-DEPI-Project/') {
        //     sh 'kubectl apply -f deployment.yaml'
        //     sh 'kubectl apply -f service.yaml'
        // }
        // } // Uncomment this closing bracket if you are using a kubeconfig file
    // }
}

return this