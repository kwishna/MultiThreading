node('master'){
    
    stage('Git Pull') {
        
        git credentialsId: 'Krishna', url: 'https://github.com/kwishna/MultiThreading.git'
    }
    
    stage('Build') {
        
            if (isUnix()) {
                sh 'mvn -Dmaven.test.failure.ignore clean package'
            }
            else {
                bat("""
                        cd ${WORKSPACE}
                        CALL mvn clean compile exec:java -DmainClass="Main"
                        CALL file_backup.bat
                    """)
            }
    }
    
    stage('Prepare Bat File') {
        
            for (int i = 0; i < 3; i++) {
                    stage("Test ${i}") {
                        bat("""
                            cd ${WORKSPACE}
                            mvn clean compile exec:java -DmainClass="jenkins.Reader"
                        """)
                    }
            }
    }
    
    stage('Run Bat File') {
        
        bat("""
                CALL rerun.bat
                CALL file_backup.bat
            """)
    }
}