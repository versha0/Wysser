// pipeline {
//     agent {
//            docker {
//                  image 'maven:3-alpine'
//                  args '-v /var/lib/jenkins/.m2:/root/.m2'
//            }
//    }
//
//     stages {
//         stage('Build') {
//           steps {
//
//                 sh 'mvn -B -DskipTests clean compile package'
//             }
//         }
// 	    stage('Test') {
//
// 	        steps {
//                 sh 'mvn test'
//             }
//         }
//     }
// }
//
pipeline {
    agent any
    stages {
     stage('Get Into AWS and build') {
            steps {
                sh 'cd;pwd;ls -la .ssh/; less .ssh/id_rsa.pub'
                sh 'whoami'
                sh 'ssh ubuntu@15.206.105.26 "cd stackroute-product;sudo docker-compose down --remove-orphans"'
                sh 'ssh ubuntu@15.206.105.26 "cd stackroute-product;  git checkout v1.0.2"'
                sh 'ssh ubuntu@15.206.105.26 "cd stackroute-product;  git pull origin v1.0.2 "'
                sh 'ssh ubuntu@15.206.105.26 "cd stackroute-product/angular-frontend; npm install; ng build "'
                sh 'ssh ubuntu@15.206.105.26 "cd stackroute-product; mvn -DskipTests clean compile package "'
                sh 'ssh ubuntu@15.206.105.26 "cd stackroute-product; sudo docker-compose up -d --build"'
            }
        }
    }
}