<?php
 //open connection to mysql db
    $connection = mysqli_connect("localhost","root","","gem") or die("Error " . mysqli_error($connection));
  //fetch table rows from mysql db
    //$sql = "select * from contrats";
    $sql= " SELECT *
FROM clients
   JOIN contrats where contrats.client_id=clients.id
   
  ;      
";
    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));
  //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
  //write to json file
   $myJSON = json_encode($emparray);

echo $myJSON;

?>