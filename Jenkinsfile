pipeline {
  agent any
  stages {
    stage('scm') {
      steps {
        git 'https://github.com/arulmaniyan89/GetAnalysisSelenium.git'
      }
    }
    stage ('build') {
      steps {
        withMaven(maven : 'mymaven'){
        bat "mvn clean install"
      }
    }
  }
}
}
