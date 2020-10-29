Setting up project:
	To run Angular you need to install:
		Node.js - https://nodejs.org/en/
		Angular CLI - Open the terminal window and run the following command: npm install -g @angular/cli
	Next you need to open up the project location and run the command: npm -install
	Finally to run the project you need to use the following command: ng serve --open

Issues:
	Total Page Count: 
		Wordpress returns a header field X-WP-TotalPages after using GET on the Post endpoint but I do not know how to access the headers to pull the value. 
		As a work around I have set the total page count to 50.
	Images:
		For my images I called the service to get 100 results(wordpress max request ammount) from the api so not all the images for every post is available.
		The way I want to pull the images in is to use the already created post array and take the featured_media value to ping the .../media/{featured_media}
		endpoint then store the results into an array so only the results that you need are pulled.  I have worked with a REST API in Java and could easily make 
		single api calls and store them into an array for easy reference, however I tried doing that in Angular and its not working.  I'm not sure why it won't work,
		but my best guess is it has something to do with observables and having to subscribe to the service that makes it not work the way I want.  I need to
		do more research to figure out the correct way to do it.

Features:
	All required features added
	Styling: there is no bootstrap, all CSS is hand written.
	404 page not found is the only exception and not my creation (used free code from: https://codepen.io/juliepark/pen/erOoeZ).
		- to test 404 page either disconnect internet (may have to hold Ctrl and click on the refresh button to hard reset), or go into the post.service.ts file 
		under services and change the endpoint to something wrong.
	Dynamic menu bar and webpage: changes view based on screen size.
	Mobile setting begin at 600px and support down to 360px sceens.
	background that stays in place when you scroll.
	Added shadow hover effects to buttons.
	Anything that can be clicked has a pointer change on hover.
	smooth scrolling to top of page when clicking previous or next page.
	when clicking read more a new tab is opened with the article from Envano.com.
	image not found text and icon if blog image is not found.
	default placeholder shown if author picture is not available.

Features I Want To Add:
	I would like to add this effect to the buttons - https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_buttons_animate1
	When leaving the webpage and returning or refreshing the webpage it will go to the last page you were on.
	Working menu bar that actually routes you to different tabs even if there is nothing in the view.

Extra Thoughts:
	instead of having the next or previous links in mobile, I think it might be cool if while scrolling it auto loads the next posts,
	kind of like how facebook loads more posts.
	Overall this was a pretty fun project to do!

Questions For Designer:
	Why only support for above 360px? What about older phones that have a smaller screen size?
	What is the font used on the example?
	Why only a previous and next page link? It might be useful to add page numbers and link to first and last page for quicker navigation.