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
<script src="js/jquery.js"></script>
<script src="js/editProfile.js"></script>
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
	width: 70%;
}

.rightContainer {
	height: 100%;
	width: 30%;
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
<script type="text/javascript">
	$(document).ready(function() {
		document.getElementById("mySubmit").disabled = true;
	});

	function checkPass() {

		//	Store the password field objects into variables ...
		var pass1 = document.getElementById('pass1');
		var pass2 = document.getElementById('pass2');
		//	Store the Confimation Message Object ...
		var message = document.getElementById('confirmMessage');
		//	Set the colors we will be using ...
		var goodColor = "#66cc66";
		var badColor = "#ff6666";
		//	Compare the values in the password field 
		//	and the confirmation field

		if (pass1.value == pass2.value && pass1.value.length != 0) {
			//		The passwords match. 
			//		Set the color to the good color and inform
			//		the user that they have entered the correct password 
			pass2.style.backgroundColor = goodColor;
			message.style.color = goodColor;
			message.innerHTML = "Passwords Match!";
			var fname = document.getElementById('fname');
			var lname = document.getElementById('lname');
			if (fname.value.length != 0 && lname.value.length != 0) {
				document.getElementById("mySubmit").disabled = false;
			}
	
		} else if (pass1.value != pass2.value) {
			//		The passwords do not match.
			//		Set the color to the bad color and
			//		notify the user.
			pass2.style.backgroundColor = badColor;
			message.style.color = badColor;
			message.innerHTML = "Passwords Do Not Match!"
			document.getElementById("mySubmit").disabled = true;

		}
	}
</script>
</head>
<body>
	<%
		session.setAttribute("user", "devva");
	%>
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
				<li><a data-toggle="tab" href="#products">Products</a></li>
				<li><a data-toggle="tab" href="#login">Login</a></li>
				<li><a data-toggle="tab" href="#signup">Signup</a></li>
				<li><a data-toggle="tab" href="#contact"> Contact </a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>

	<!-- Page Content -->

	<div class="container">

		<div class="tab-content">
			<div id="home" class="tab-pane fade in active">
				<!-- <h1> Welcome to GroceryKart! </h1> -->


				<div class="row">
					<!-- 
                 <div class="col-md-3">
                   <p class="lead">GroceryKart</p>
                   <div class="list-group">
                     <a href="#" class="list-group-item">Snacks</a>
                     <a href="#" class="list-group-item">Category 2</a>
                     <a href="#" class="list-group-item">Category 3</a>
                   </div>
                 </div>
                 -->
					<div class="row carousel-holder">

						<div class="col-md-12">
							<div id="carousel-example-generic" class="carousel slide"
								data-ride="carousel">
								<ol class="carousel-indicators">
									<li data-target="#carousel-example-generic" data-slide-to="0"
										class="active"></li>
									<li data-target="#carousel-example-generic" data-slide-to="1"></li>
									<li data-target="#carousel-example-generic" data-slide-to="2"></li>
								</ol>
								<div class="carousel-inner">
									<div class="item active">
										<a href="swissroll.jsp"> <img class="slide-image"
											src="images/swissroll_large.jpg" alt="">
										</a>
									</div>
									<div class="item">
										<a href="peanutcups.jsp"> <img class="slide-image"
											src="images/peanut_butter_large.jpg" alt="">
										</a>
									</div>
									<div class="item">
										<a href="kitkat.jsp"> <img class="slide-image"
											src="images/kitkat_large.jpg" alt="">
										</a>
									</div>
								</div>
								<a class="left carousel-control"
									href="#carousel-example-generic" data-slide="prev"> <span
									class="glyphicon glyphicon-chevron-left"></span>
								</a> <a class="right carousel-control"
									href="#carousel-example-generic" data-slide="next"> <span
									class="glyphicon glyphicon-chevron-right"></span>
								</a>
							</div>
						</div>

					</div>

					<div class="row">

						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img src="images/granola_bars.jpg" alt="">
								<div class="caption">
									<h4 class="pull-right">$4.99</h4>
									<h4>
										<a href="#">Granola Bars</a>
									</h4>
									<div class="addtocart">
										<input type="button" value="Add to cart" class="buybutton"
											onclick="simpleCart.add('name=Granola Bars','price=4.99','image=images/thumbs/granola_bars.jpg');return false;" />
									</div>

								</div>
								<div class="ratings">
									<p class="pull-right">15 reviews</p>
									<p>
										<span class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span>
									</p>
								</div>
							</div>
						</div>

						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img src="images/swiss_roll.jpg" alt="">
								<div class="caption">
									<h4 class="pull-right">$1.99</h4>
									<h4>
										<a href="#">Swiss Rolls</a>
									</h4>
									<div class="addtocart">
										<input type="button" value="Add to cart" class="buybutton"
											onclick="simpleCart.add('name=Swiss Rolls','price=4.99','image=images/thumbs/swiss_roll.jpg');return false;" />
									</div>
								</div>
								<div class="ratings">
									<p class="pull-right">12 reviews</p>
									<p>
										<span class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star-empty"></span>
									</p>
								</div>
							</div>
						</div>

						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img src="images/chewy.png" alt="">
								<div class="caption">
									<h4 class="pull-right">$1.50</h4>
									<h4>
										<a href="#">Chewy Cups</a>
									</h4>
									<div class="addtocart">
										<input type="button" value="Add to cart" class="buybutton"
											onclick="simpleCart.add('name=Chewy Cups','price=4.99','image=images/thumbs/chewy.png');return false;" />
									</div>
								</div>
								<div class="ratings">
									<p class="pull-right">31 reviews</p>
									<p>
										<span class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star-empty"></span>
									</p>
								</div>
							</div>
						</div>

						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img src="images/bravo.jpeg" alt="">
								<div class="caption">
									<h4 class="pull-right">$3.99</h4>
									<h4>
										<a href="#">Bravo Biscuits</a>
									</h4>
									<div class="addtocart">
										<input type="button" value="Add to cart" class="buybutton"
											onclick="simpleCart.add('name=Bravo','price=4.99','image=images/thumbs/bravo.jpg');return false;" />
									</div>
								</div>
								<div class="ratings">
									<p class="pull-right">6 reviews</p>
									<p>
										<span class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star-empty"></span> <span
											class="glyphicon glyphicon-star-empty"></span>
									</p>
								</div>
							</div>
						</div>

						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img src="images/min_maid.png" alt="">
								<div class="caption">
									<h4 class="pull-right">$0.99</h4>
									<h4>
										<a href="#">Minute Maid</a>
									</h4>
									<div class="addtocart">
										<input type="button" value="Add to cart" class="buybutton"
											onclick="simpleCart.add('name=Minute Maid','price=4.99','image=images/thumbs/minute_maid');return false;" />
									</div>
								</div>
								<div class="ratings">
									<p class="pull-right">18 reviews</p>
									<p>
										<span class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star-empty"></span>
									</p>
								</div>
							</div>
						</div>

						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img src="images/viscount.jpg" alt="">
								<div class="caption">
									<h4 class="pull-right">$4.99</h4>
									<h4>
										<a href="#">Viscount</a>
									</h4>
									<div class="addtocart">
										<input type="button" value="Add to cart" class="buybutton"
											onclick="simpleCart.add('name=Viscount','price=4.99','image=images/thumbs/viscount.jpg');return false;" />
									</div>
								</div>
								<div class="ratings">
									<p class="pull-right">18 reviews</p>
									<p>
										<span class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star"></span> <span
											class="glyphicon glyphicon-star-empty"></span>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div id="products" class="tab-pane fade in ">

				<div class="header">
					<div class="container">
						<div class="row">
							<div class="col-md-6"></div>
							<!--
                    <div class="col-md-3 shipping text-right">
                      <img src="https://pantrykart.com/skin/frontend/indialet/default/images/free-shipping.png" alt="Free Shipping On orders Over $50">
                    </div> -->

						</div>
					</div>
				</div>




				<!-- <div class="row">
            
            <div class="col-sm-2 col-lg-2 col-md-2">
                <div class="thumbnail">
                  <img src="images/funkysnacks.jpg" alt="">
                  <div class="caption"> 
                    <h5 class="pull-right">$4.99</h5>
                    <h5><a href="#">Funky Monkey Snacks</a>
                    </h5>
                    <div class="addtocart"><input type="button" value="Add to cart" class="buybutton" onclick="ajaxaddcart(0)" /></div>
                    
                  </div>  
                  
                </div>
            </div>

            <div class="col-sm-4 col-lg-2 col-md-2">
              <div class="thumbnail">
                <img src="images/peanut_butter.png" alt="">
                <div class="caption">
                  <h5 class="pull-right">$1.99</h5>
                  <h5><a href="#">Peanut Butter Cups</a>
                    </h5>
                  <div class="addtocart"><input type="button" value="Add to cart" class="buybutton" onclick="ajaxaddcart(0)" /></div>
                </div>
              </div>
            </div>
            
            <div class="col-sm-4 col-lg-2 col-md-2">
              <div class="thumbnail">
                <img src="images/kitkat.jpg"  alt="">
                <div class="caption">
                  <h5 class="pull-right">$1.50</h5>
                  <h5><a href="#">Kit Kat</a>
                  </h5>
                  <div class="addtocart"><input type="button" value="Add to cart" class="buybutton" onclick="ajaxaddcart(0)" /></div>
                </div>
              </div>
            </div>
            
            <div class="col-sm-2 col-lg-2 col-md-2">
              <div class="thumbnail">
                <img src="images/soft_drinks.jpg" alt="">
                  <div class="caption">
                    <h4 class="pull-right">$3.99</h4>
                    <h4><a href="#">Soft Drinks</a>
                    </h4>
                    <div class="addtocart"><input type="button" value="Add to cart" class="buybutton" onclick="ajaxaddcart(0)" /></div>
                  </div>
              </div>
            </div>
          </div>  
          <div class="row">  
            <div class="col-sm-2 col-lg-2 col-md-2">
              <div class="thumbnail">
                <img src="images/donuts.JPG" alt="">
                <div class="caption">
                  <h5 class="pull-right">$0.99</h5>
                  <h5><a href="#">Yummy Donuts</a>
                  </h5>
                  <div class="addtocart"><input type="button" value="Add to cart" class="buybutton" onclick="ajaxaddcart(0)" /></div>
                </div>
              </div>
            </div>

            <div class="col-sm-2 col-lg-2 col-md-2">
              <div class="thumbnail">
                  <img src="images/cookies.jpg" alt="">
                  <div class="caption">
                    <h5 class="pull-right">$4.99</h5>
                    <h5><a href="#">Tasty Cookies</a>
                    </h5>
                    <div class="addtocart"><input type="button" value="Add to cart" class="buybutton" onclick="simpleCart.add('name=Cookie','price=4.99','image=images/cookies.jpg');return false;" /></div>
                  </div>
              </div>
            </div>
          </div> -->
				<div id="content">
					<input class="search" placeholder="Search" />
					<button class="sort" data-sort="name">Sort by name</button>

					<!--Here's the Catalog Items. You can make anything into a product, 
                  just copy and paste the onclick attribute from one of the products 
                  below.
                -->
					<!-- <div id="header"> 
                <h2> Search </h2> 
              </div> -->
					<ul id="catalog" class="list">

						<!-- <li data-id="7"> -->
						<!--   <img class="image" src="images/images/funkysnacks.jpg" alt="Funky Snacks" /> -->
						<!--   <span class="price">$4.99</span> -->
						<!-- 		  <p class="name" ><b><a href="funkysnacks.jsp" style="color:black;">Funky Snacks</a><br/><a href="#" onclick="simpleCart.add('name=Funky Snacks','price=4.99','image=images/images/thumbs/funkysnacks.jpg');return false;">add to cart</a></b></p> -->
						<!-- </li> -->

						<li data-id="2"><img class="image"
							src="images/images/peanut_butter.png" alt="Peanut_butter " /> <span
							class="price">$1.99</span>
							<p class="name">
								<b><a href="funkysnacks.jsp" style="color: black;">Peanut
										Cups</a><br /> <a href="#"
									onclick="simpleCart.add('name=Peanut Cups','price=1.99','image=images/images/thumbs/peanut_butter.png');return false;">
										add to cart</a>
									<form action="ReviewControllerServlet" method="POST">
										<p class="submit">
											<input type="submit" id="product_name" name="Peanut Cups"
												value="Reviews">
										</p>
									</form></b>
							</p></li>

						<li data-id="3"><img class="image"
							src="images/images/kitkat.jpg" alt="White Slip-on" /> <span
							class="price">$1.50</span>
							<p class="name">
								<a href="funkysnacks.jsp" style="color: black;"><b>Kit
										Kat</a><br /> <a href="#"
									onclick="simpleCart.add('name=Kit Kat','price=1.50','image=images/images/thumbs/kitkat.jpg');return false;">
									add to cart</a>
							<form action="ReviewControllerServlet" method="POST">
								<p class="submit">
									<input type="submit" id="product_name" name="Kit Kat"
										value="Reviews">
								</p>
							</form> </b>
							</p></li>

						<li data-id="4"><img class="image"
							src="images/images/soft_drinks.jpg" alt="Black Gold" /> <span
							class="price">$3.99</span>
							<p class="name">
								<b><a href="funkysnacks.jsp" style="color: black;">Soft
										Drinks</a><br /> <a href="#"
									onclick="simpleCart.add('name=Soft Drinks','price=3.99','image=images/images/thumbs/soft_drinks.jpg');return false;">
										add to cart</a>
									<form action="ReviewControllerServlet" method="POST">
										<p class="submit">
											<input type="submit" id="product_name" name="Soft Drinks"
												value="Reviews">
										</p>
									</form></b>
							</p></li>

						<li data-id="5"><img class="image"
							src="images/images/donuts.JPG" alt="Green Stripe" /> <span
							class="price">$0.99</span>
							<p class="name">
								<b><a href="funkysnacks.jsp" style="color: black;">Yummy
										Donut</a><br /> <a href="#"
									onclick="simpleCart.add('name=Yummy Donuts','price=0.99','image=images/images/thumbs/donuts.JPG');return false;">
										add to cart</a>
									<form action="ReviewControllerServlet" method="POST">
										<p class="submit">
											<input type="submit" id="product_name" name="Yummy Donut"
												value="Reviews">
										</p>
									</form></b>
							</p></li>

						<li data-id="6"><img class="image"
							src="images/images/cookies.jpg" alt="Red Stripe" /> <span
							class="price">$4.99</span>
							<p class="name">
								<b><a href="funkysnacks.jsp" style="color: black;">Tasty
										Cookies</a><br /> <a href="#"
									onclick="simpleCart.add('name=Tasty Cookies','price=4.99','image=images/images/thumbs/cookies.jpg');return false;">
										add to cart</a>
									<form action="ReviewControllerServlet" method="POST">
										<p class="submit">
											<input type="submit" id="product_name" name="Tasty Cookies"
												value="Reviews">
										</p>
									</form></b>
							</p></li>

						<li data-id="7"><img class="image"
							src="images/images/swiss_roll.jpg" alt="Black Gold" /> <span
							class="price">$3.99</span>
							<p class="name">
								<b>Swiss Roll<br /> <a href="#"
									onclick="simpleCart.add('name=Swiss Roll','price=3.99','image=images/images/thumbs/swiss_roll.jpg');return false;">
										add to cart</a>
									<form action="ReviewControllerServlet" method="POST">
										<p class="submit">
											<input type="submit" id="product_name" name="Swiss Roll"
												value="Reviews">
										</p>
									</form></b>
							</p></li>

						<!--   <li data-id="8"> -->
						<!--     <img class="image" src="images/images/chewy.png" alt="Green Stripe" /> -->
						<!--     <span class="price">$0.99</span> -->
						<!--   		  <p class="name"><b>Chewy_cups<br/><a href="#" onclick="simpleCart.add('name=Yummy Donuts','price=0.99','image=images/images/thumbs/donuts.JPG');return false;"> add to cart</a></b></p> -->
						<!--   </li> -->

						<!--   <li data-id="9"> -->
						<!--     <img class="image" src="images/images/bravo.jpeg" alt="Red Stripe" /> -->
						<!--     <span class="price">$4.99</span> -->
						<!--   		  <p class="name"><b>Tasty Cookies<br/><a href="#" onclick="simpleCart.add('name=Tasty Cookies','price=4.99','image=images/images/thumbs/cookies.jpg');return false;"> add to cart</a></b></p> -->
						<!--   </li> -->
						<!--   <li data-id="10"> -->
						<!--     <img class="image" src="images/images/min_maid.png" alt="Black Gold" /> -->
						<!--     <span class="price">$3.99</span> -->
						<!--   		  <p class="name"><b>Soft Drinks<br/><a href="#" onclick="simpleCart.add('name=Soft Drinks','price=3.99','image=images/images/thumbs/soft_drinks.jpg');return false;"> add to cart</a></b></p> -->
						<!--   </li> -->


					</ul>
					<!--/ Catalog-->


					<div id="sidebar">
						<h2>Your Cart</h2>

						<!--Add a Div with the class "simpleCart_items" to show your shopping cart area.-->
						<div class="simpleCart_items"></div>

						<div>
							<!--Here's the Links to Checkout and Empty Cart-->
							<a href="#" class="simpleCart_empty">empty cart</a> <a href="#">Please
								Login to Checkout</a>


							<!--End #sidebar-->
						</div>

					</div>

				</div>

				<script>
					var options = {
						valueNames : [ 'price', 'name', {
							data : [ 'id' ]
						}, {
							attr : 'src',
							name : 'image'
						}, ]
					};
					var userList = new List('content', options);
				</script>


			</div>


			<div id="login" class="tab-pane fade in">

				<h1>Login</h1>
				<form method="post" action="SessionControllerServlet">
					<p>
						<input type="text" name="login" value=""
							placeholder="Username or Email">
					</p>
					<p>
						<input type="password" name="password" value=""
							placeholder="Password">
					</p>
					<p class="remember_me">
						<label> <input type="checkbox" name="remember_me"
							id="remember_me"> Remember me on this computer
						</label>
					</p>
					<p class="submit">
						<input type="submit" name="commit" value="Login">
					</p>
				</form>


				<div class="login-help">
					<p>
						Forgot your password? <a href="index.html">Click here to reset
							it</a>.
					</p>
				</div>
			</div>


			<div id="signup" class="tab-pane fade in">
				<h1>Fill the form below to register with us!</h1>
				<form action="RegisterControllerServlet" method="POST">
					First name: <br> <input type="text" id="fname"
						name="firstname" maxlength="15"> </input> <br> Last name: <br>
					<input type="text" id="lname" name="lastname" maxlength="15">
					</input> <br> Email Id: <br> <input type="email" id="email"
						name="emailId" onkeyup="checkEmail();"></input> <br>
					Password: <br> <input type="password" id="pass1" name="pass"
						maxlength="15"></input> <br> Confirm Password: <br> <input
						type="password" id="pass2" name="pass" onkeyup="checkPass();"
						maxlength="15"></input> <span id="confirmMessage"
						class="confirmMessage"></span> <br> <input type="checkbox"
						name="mailing" value=""> Would you like to receive mails
					from us? </input> <br> <input type="submit" value="Submit"
						id="mySubmit" onclick="checkPass2();"> </input> <input
						type="reset" value="Reset"> </input>
				</form>
			</div>
			<div id="contact" class="tab-pane fade in">
				<!-- <div align="center"> -->
				<h1>How can we help you with?</h1>
				<form action="EmailSendingServlet" method="POST">
					Email Address: <br> <input type="email" name="email">
					</input> <br> Subject: <br> <input type="text" name="subject"
						maxlength="15"> </input> <br> Message: <br>
					<textarea rows="10" cols="40" name="content"></textarea>

					<br> <input type="submit" value="Submit" onclick="submit()">
					</input>
				</form>
				<!--  </div> -->
			</div>
			<!-- /.container -->
		</div>
	</div>
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
	<script src="js/simpleCart_uncompressed.js" type="text/javascript"
		charset="utf-8"></script>

	<!--Make a new cart instance with your paypal login email-->
	<script type="text/javascript">
		simpleCart = new cart("brett@wojodesign.com");
	</script>

</body>
</html>