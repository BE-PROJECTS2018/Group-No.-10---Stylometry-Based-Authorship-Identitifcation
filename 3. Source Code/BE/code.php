<!DOCTYPE html>
<html>
<body>

<h1>My first PHP page</h1>

<?php
//$message = exec("C:/xampp/htdocs/test/hello.py 2>&1");
//print_r($message);

ini_set('max_execution_time', 30000); //300 seconds = 5 minutes

$python = "C:/Users/Shashank/AppData/Local/Programs/Python/Python36/python.exe";
//$file = "C:/xampp/htdocs/test/hello.py";
$file = " C:/xampp/htdocs/try/testingWord.py";
$cmd = "$python $file";
//$output=exec($python . " " . $file);
$op = exec($cmd);
echo $op;

?>

</body>
</html>