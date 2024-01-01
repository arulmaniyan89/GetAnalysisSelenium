pipeline {
  agent any
  tools {
    maven 'maven-3.6.3' // specify the Maven version to use
  }
  stages {
    stage ('Checkout') {
      steps {
        git 'https://github.com/arulmaniyan89/GetAnalysisSelenium.git' // clone the Git repository
      }
    }
    stage ('Build') {
      steps {
        sh 'mvn -f GetMoneyRichAutomation/pom.xml clean package' // run the Maven command with the pom.xml file in the subdirectory
      }
    }
    stage ('Test') {
      steps {
        sh 'mvn -f GetMoneyRichAutomation/pom.xml test' // run the Maven test command
      }
      post {
        always {
          junit 'GetMoneyRichAutomation/target/surefire-reports/*.xml' // publish the test results
        }
      }
    }
  }
}
