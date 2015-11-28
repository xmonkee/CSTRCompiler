path=$1
echo "Compiling to java bytecode" 
sbt "run $path" 
filename=$(basename "$path")
progname="${filename%.*}"
jname="${progname}.j"
echo "File $jname created"
echo "Compiling to class file"
java -jar jasmin.jar "$jname"
echo "Running program"
java $progname




