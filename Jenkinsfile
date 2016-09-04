node {
   // Mark the code checkout 'stage'....
   stage 'Checkout'

   // Checkout code from repository
   checkout scm
   
   // Mark the code build 'stage'....
   stage 'Build'
   cd SimpleStocks
   // Run the maven build
   sh "mvn clean install"
}
