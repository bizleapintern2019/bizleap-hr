cd bizleap-commons/
echo "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Common Build Starts >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
mvn clean install -Dmaven.test.skip=true

cd ..
cd bizleap-hr/
echo "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< HR Build Starts >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
mvn clean install -Dmaven.test.skip=true
cd ..
