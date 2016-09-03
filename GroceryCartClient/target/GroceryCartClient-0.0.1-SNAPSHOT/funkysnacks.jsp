<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.json.simple.*"%>
<%@page import="org.json.simple.parser.JSONParser"%>
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
				<li><a data-toggle="tab" href="#products">Reviews</a></li>


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
							<img
								src="images/large_images/<%=session.getAttribute("pname")%>.jpg"
								alt="">
							<div class="caption">
								<h4 class="pull-right">$4.99</h4>
								<h4><%=session.getAttribute("pname")%></h4>
							</div>
						</div>
					</div>

					<!-- <div id="about_product"> -->
					<!-- <li> -->
					<!--   <h3> About the Product</h3> -->

					<!--     <li>Funky Monkey Snacks are delicious</li> -->
					<!--     <li> All natural, freeze-dried fruit that crunches </li> -->
					<!--     <li> Slices and pieces of real fruit and satisfying crunch </li>  -->
					<!--     <li> No added sugar and no artificial colors, flavors or preservatives </li> -->
					<!--     <li> Ships in Certified Frustration-Free Packaging </li> -->

					<!-- </li> -->
					<!-- </div> -->
					<!-- <br/> -->
					<h3>Reviews</h3>
					<%
						JSONParser parser = new JSONParser();
						if (session.getAttribute("json").toString().compareTo("NO")==0) {%>
								<h2>No Reviews</h2>
						<%} else {
							JSONArray json = (JSONArray) parser.parse(session.getAttribute("json").toString());
							for (int i = 0; i < json.size(); i++) {
								JSONObject j = (JSONObject) json.get(i);
					%>
					<div class="review">
						<li>By <b> <%=j.get("user")%></b></li>
						<%=j.get("review")%>

					</div>

					<%
						}
						}
					%>
					<%
						if ((session.getAttribute("user").toString() != null)
									&& (session.getAttribute("purchased").toString().compareTo("false") != 0)) {
					%>
					
					<form action="AddReviewControllerServlet" method="post">

						<b>Write a review: </b> <br>
						<textarea rows="10" cols="40" name="review"></textarea>


						</input> <input type="submit" value="Submit" onclick="submit()"> </input>
					</form>
					<br />
					<%
						}
					%>
					<form action="GoBackServlet" method="POST">
						<input type="submit" value="Back" onclick="submit()">
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