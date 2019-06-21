cd bizleap-commons/
echo "<<<<<<<<<<<<<<Bizleap-commons build start<<<<<<<<<<<<<<<<<<"
mvn clean install -Dmaven.test.skip=true

cd ..
cd bizleap-hr/
echo "<<<<<<<<<<<<<<<<<<<<<<Bizleap-hr build start<<<<<<<<<<<<<<<<<"
mvn clean install -Dmaven.test.skip=true

cd..
