pipeline {
    agent any
    stages {
stage('scm') {
steps {
    git 'https://github.com/ganeshlovesdevops/maven_demo.git'
    }
}
    stage('build') {
    steps {
        withMaven(maven : 'mymaven'){
        bat "mvn clean install"
    }
    }
}
    stage('junit') {
steps {
    junit healthScaleFactor: 10.0, testResults: '**/gameoflife-web/target/surefire-reports/*.xml'
    }
}
    stage('deploy') {
steps {
    bat 'copy "C:\\Program Files (x86)\\Jenkins\\workspace\\raghupipeline\\gameoflife-web\\target\\*.war" "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\"'
    }
}
}
}
