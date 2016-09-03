<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>GroceryKart</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/shop-homepage.css" rel="stylesheet">

<script src="dist/list.js"></script>


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<style>
.cartHeaders, .totalRow {
	display: none;
}

.simpleCart_items {
	overflow-y: auto;
	overflow-x: hidden;
	height: 324px;
	width: 243px;
	margin-bottom: 20px;
}

.itemContainer {
	clear: both;
	width: 229px;
	padding: 11px 0;
	font-size: 11px;
}

.itemImage {
	float: left;
	width: 60px;
}

.itemName {
	float: left;
	width: 85px;
}

.itemPrice {
	float: left;
	width: 85px;
	color: #418932;
}

.itemQuantity {
	float: left;
	width: 33px;
	margin-top: -12px;
	vertical-align: middle;
}

.itemQuantity input {
	width: 20px;
	border: 1px solid #ccc;
	padding: 3px 2px;
}

.itemTotal {
	float: left;
	color: #c23f26;
	margin-top: -6px
}

.leftContainer {
	height: 100%;
	width: 40%;
}

.rightContainer {
	height: 100%;
	width: 60%;
}

#productContainer div {
	float: left; # Info { width : 400px;
	text-align: center;
	margin: 50px auto;
	/* Move it to the right */
	/* For visibility. Delete me */
	border: 1px solid blue;
}
}
</style>



<title>Insert title here</title>
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->


		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" data-toggle="tab" href="#home">GroceryKart</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a data-toggle="tab" href="#products"></a></li>


			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>

	<!-- Page Content -->

	<div class="container">

		<div class="tab-content">


			<div id="products" class="tab-pane fade in active">


				<div class="row">

					<div class="col-sm-4">
						<div class="thumbnail">
							<img src="images/kitkat_large.jpg" alt="">
							<div class="caption">
								<h4 class="pull-right">$1.50</h4>
								<h4>Kit Kat</h4>
							</div>
						</div>
					</div>

					<div id="about_product">
						<li>
							<h3>About the Product</h3>
						<li>Kit Kat is a chocolate-covered wafer biscuit bar
							confection created by Rowntree's of York, England, and is now
							produced globally by Nestlé, which acquired Rowntree in 1988,[1]
							with the exception of the United States where it is made under
							license by H.B. Reese Candy Company, a division of The Hershey
							Company.</li>
						<li>The standard bars consist of two or four fingers composed
							of three layers of wafer, separated and covered by an outer layer
							of chocolate. Each finger can be snapped from the bar separately.
							There are many different flavours of Kit Kat.</li>
						</li>
					</div>
					<br />

					<form action="GoBackServlet" method="POST">

						<input type="submit" value="Back" onclick="submit()"> </input>
					</form>

				</div>
			</div>

			<div id="login" class="tab-pane fade in"></div>


			<div id="signup" class="tab-pane fade in"></div>
			<div class="container">

				<hr>

				<!-- Footer -->
				<footer>
				<div class="row">
					<div class="col-lg-12">
						<p>Copyright &copy; GroceryKart 2016</p>
					</div>
				</div>
				</footer>

			</div>
			<!-- /.container -->

			<!-- jQuery -->
			<script src="js/jquery.js"></script>

			<!-- Bootstrap Core JavaScript -->
			<script src="js/bootstrap.js"></script>

			<!-- Bootstrap Tabs -->
			<script type="text/javascript" src="js/bootstrap-tab.js"></script>

			<!-- Simple Cart -->
			<script src="js/simpleCart.js" type="text/javascript" charset="utf-8"></script>

			<!--Make a new cart instance with your paypal login email-->
			<script type="text/javascript">
      simpleCart = new cart("t.phani.kiran@gmail.com");
    </script>
</body>
</html>