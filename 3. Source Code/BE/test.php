<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
body {
    font-family: inherit;
    background-image: url("background.jpg");
}

h1{
    text-shadow: 2px 2px 5px grey;
}

p{
    text-shadow: 2px 2px 4px grey;
}
.sidenav {
    height: 100%;
    width: 250px;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    padding-top: 20px;
}

.sidenav a {
    padding: 6px 8px 6px 16px;
    text-decoration: none;
    font-size: 18px;
    color: #818181;
    display: block;
}

.sidenav a:hover {
    color: #f1f1f1;
}

.main {
    margin-left: 250px; /* Same as the width of the sidenav */
    font-size: 18px; /* Increased text to enable scrolling */
    padding: 0px 10px;
}

@media screen and (max-height: 450px) {
    .sidenav {padding-top: 15px;}
    .sidenav a {font-size: 18px;}
}

ul
{
list-style-type: none;
}
</style>
</head>

<body style="color:white;">
<script>
function myFunction() {
    var result="<?php  py_call(); ?>";
    var s= document.getElementById('res');
    s.value = result

  return false;
}
</script>
<div class="sidenav">
  <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="index.html">
                        SAI
                    </a>
                </li>
                <li>
				    
                    <a href="upload.html">Upload a file</a>
					
                </li>
                <li>
                    <a href="test.php">Analyse</a>
                </li>
                <li>
                    <a href="charts.html">Comparitive Charts</a>
                </li>
                <li>
                    <a href="about.html">About</a>
                </li>
                <li>
                    <a href="https://ves.ac.in/vesit/contact-us/">Contact</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->
</div>

<div class="main">
    <div class="container">
        <h1 style="color:red; text-align: center">Author of the document</h1>
        <form method="post">
            <button class="btn btn-lg btn-info" onclick="myFunction()">Predict</button>
        </form>
        <!-- <textarea style="color:red" id="res" rows="4" cols="50"></textarea> -->
        
        <div class="row">
            <div class="col-sm-8-pull-4">
                <div class="thumbnail">
                    <a href="chart.png" target="_blank">
                    <img class="img-responsive" src="chart.png" alt="Comparitive">
                    <div class="caption">
                        <p style="text-align: center">Prediction</p>
                    </div>
                    </a>
                </div>
            </div>
        </div>
    </div> 

<?php
ini_set('max_execution_time', 3000); //300 seconds = 5 minutes 
function py_call()
{
    $python = "C:/Users/Admin/AppData/Local/Programs/Python/Python36/python.exe";
    $file = "C:/xampp/htdocs/BE/test.py";
    $cmd = "$python $file";
    // $output=exec($python . " " . $file);
    $op = exec($cmd);
    echo $op;
}

?>
</div>
 
</body>
</html>