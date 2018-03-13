<?php
	$userid=$_GET['userid'];
	$pwd=$_GET['pwd'];
	//echo .$userid;
	
	
	

	exec(("java skser saurav"), $output);
	echo $output[0];
?>
