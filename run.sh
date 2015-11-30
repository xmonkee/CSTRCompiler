path=$1

echo "Compiling to java bytecode" 
sbt "run $path" 
filename=$(basename "$path")
progname="${filename%.*}"
echo "File ${progname}.j created"

echo "Compiling to class file"
java -jar jasmin.jar "${progname}.j"

echo "Moving to testout directory"
mkdir -p testout
mv ${progname}.class testout/
mv ${progname}.j testout/

echo "Generating Standard Library"
javac Lib.java
mv Lib.class testout/

echo "Running program"
java -cp testout/ $progname




