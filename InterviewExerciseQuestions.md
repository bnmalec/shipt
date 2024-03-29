﻿Shipt - QA Engineer - Interview Exercise

1. Navigate to www.shipt.com.  Choose a feature of the site and write a description or test case to describe how to test the feature.

	> Test the Be a Shopper feature.  Feature should have prominent links in header and footer pointing to the same url: https://www.shipt.com/be-a-shopper/.  Application can be navigated by keys or by clicking mouse, so entire flow should be completed via both mechanisms on all supported browsers and resolutions.  Watch for any performance degradations or wonky behavior (like bouncing to a different field than expected or something like that) while using either method in any of the browsers.  All required fields should be indicated by asterisk beside the field label.  Submit each required field with no data entered, or with spaces only, and verify error message displays and makes sense.  Try a variety of inputs into text fields: special characters, foreign language characters, large text blocks.  Check that when the next and previous navigation buttons are used that the data is still saved in fields where it was entered.

	> Name fields: Check with dashes, commas, spaces between names, special characters, etc.  For example, å is a perfectly legal character and is part of many Swedish, Danish, and Norwegian names (among others).

	> Email field: Submit email field using the lists of invalid and valid email addresses from here: https://gist.github.com/cjaoude/fd9910626629b53c4d25.  This should be automated.  By the way - in the automated test I wrote for this, using that data, the shipt website allowed these invalid email addresses to be submitted: ["email@example@example.com", ".email@example.com", "email..email@example.com", "email@-example.com", "email@example.web", "email@111.222.333.44444", "Abc..123@example.com"].  I removed them from my data so the test would pass, but these are invalid email addresses and probably shouldn't be accepted by the shipt shopper application.  Also check that after the email field is submitted, and the app asks the user to validate their email address is correct, if they select N for No, the app should go back to the email field.  It currently does not.  When I click No, it moves me forward to the phone field.  The app should return me to the email field automatically so I can correct my email address.

	> Navigation buttons: Check that the user can navigate all the way from the start to the finish of the application without actually entering any data, but when the user gets to the end, the progress indicator should still say "0% completed." 

	> All other fields should be tested in a similar manner as the fields above.  Tail logs to watch for errors while testing.  Verify all saved data in the database.  Submit the form with the same data more than once and check how the records are saved.  Try refreshing the application while working on it.  Check what happens when the session expires and the user tries to come back to the application.

	> Note - while testing the be-a-shopper form, I kept running into one bug in particular.  The end of the form would be displayed and say "1 answer needs completing."  Then, upon hitting the Submit button, the error message would change to "17 answers need completing."  I got this error several times - sometimes after just navigating to the end of the form without entering any fields.  Other times I got it after entering data into a random field - it would just bounce me to the end with that error message displaying.  Sometimes I would skip all but 1 or 2 fields and then try to submit the form and get an error message like "4 answers need completing."  This is worth some investigation because this behavior would be confusing to a user. 

	> Note - the zip code field is currently allowing special characters.  The zip code and address fields are allowing large character amounts to be entered, but then the application fails to submit properly (Oops, something went wrong! message displays after user attempts to submit.  User should not be allowed to do this in the first place).

2. Locate one bug or bad workflow within the app.
	a. Explain the behavior you are seeing
	> Facebook Pixel code is breaking the `<head>` section of shipt.com. 
	> Steps to reproduce:
	> 1. Go to W3C validator and input https://www.shipt.com/ into address box, then run the check.  Or, view the source of the page and search for `<noscript>` to see the illegal tag.
	
	b. Include any useful errors or screenshots
	> Note the error from the W3C check: Bad start tag in `img` in `noscript` in `head`.  This is followed by 4 other validation errors: Stray end tag `noscript`, Stray end tag `head`, Start tag `body` seen but an element of the same type was already open, and "Cannot recover after last error. Any further errors will be ignored."
	
	
	c. Explain why and how it needs to be corrected
	> See [NoScript](https://xsymmetry.com/validation/noscript) and [Sitebulb](https://sitebulb.com/hints/indexability/head-contains-a-noscript-tag-which-includes-an-image/).  From Sitebulb: This means that the URL in question contains a `<noscript>` tag in the `<head>`, which includes an image reference. The `<noscript>` tag defines an alternate content for users that have disabled scripts in their browser or have a browser that doesn’t support script.  Whilst it can be used in both the `<head>` and the `<body>`, when used inside the `<head>`, it must contain only `<link>`, `<style>`, and `<meta>` elements. As such, including an `<img>` tag is invalid.  This can be problematic for search engine crawlers that do not render JavaScript (i.e. most crawlers, most of the time), as the presence of the `<img>` tag breaks the `<head>`, which may cause important tags (e.g. meta robots) to be missed.
	>For correction, see [Facebook Pixel Noscript is Useless in Your GTM. Here’s How You Fix It.](https://www.analyticsmania.com/post/facebook-pixel-noscript/)  This link goes over the mitigation approaches.
	
	d. What are the steps you would take to report the issue?
	> First I would check if the issue had already been reported in whatever tracking system shipt uses.  If I find nothing and if I have a relationship with the developer and we are close by, I would probably go talk to him or her just to let them know what I am seeing and to get his or her thoughts on it.  A quick conversation.  This would allow me to understand if they already know about this issue, if they already plan to address it, if it is possibly already reported but I missed it, etc.  If we are working on a new feature together in real-time, I would not enter a bug ticket.  I would just make sure my test case covers this.  If this is not a new feature in active development (in this case, it obviously isn't because we are dealing with a production website), I would enter a ticket and assign to triage.
	
	e. What priority would you give this bug? (Scale of 1-5, 1 being highest) and why?
	> Priority: 5. Definitely low priority.  More of a "buttoning up" of the code to make it more resilient for future changes, and also so you can thoroughly check the page for any really useful html validation errors, since now that bug is causing the validation to stop out.  It's fairly easy pickings to fix but low in severity.
	

3. What are the possible reasons for the following defect?  How would you go about debugging the problem and gathering more information?
	On a web application, a user adds a phone number to their account.  The user then changes the phone number.  Upon trying to re-enter the first phone number, the user is allowed to click Save, and it seems to work, but the saved number remains the second number rather than updating to the more recently entered number.  A page refresh does not change the result.
	> Possibilities: 
	> - An old user was migrated and the data isn't matching from an old schema
	> - User is entering an invalid phone number but the UI isn't showing a field validation error
	> - Load balancer has user pointed to non-master data
	> - App is pointed to wrong data source or service
	> - Some other dependency is failing in some entirely different place and blocking the update
	> - Multiple numbers are stored but the user must select a default number and still has the old number as default
	> - The browser is reading a cache-control header that okays reusing the old response without revalidation, within a time frame that has not expired yet
	> - Token is expired so the service trying to make the change is denied authorization
	> - User's session is expired when they make the change, but the website still seems to let the change occur
	> - User's edits are stored in local context but never actually persisted in a database at all, or in local storage, so refreshing the page reloads the old data
	> - Some other conflict between local state and app state - state not being managed correctly, like by a single store
	
	> This is testing complex systems - not just code, but also users, infrastructure, environment, timing...altogether.  So the thinking has to extend to interactions of all kinds when you're investigating bugs. 
	
	> I would first try to get some details: is this isolated to one user or are multiple users reporting this?  What browser is the user in when this happens?  If multiple users, can we get info on their browsers to see if there is any commonality?  Are they using a particular type of phone or tablet?  Do we know what version of the website they are on?  How long has this user existed - are they new or old?  Do we have a session we can look at in the logs?  Is the user seeing any kind of field validation at all?  What number are they trying to change it to, exactly?  Where are they seeing the old number?  Are there multiple places to see or change the number?  Who says a page refresh does not change the result - did the user report that?  Did the user try more than once?  Is there only one phone number allowed or multiple for any given user?  If we allow multiple, was the user trying to change a specific one, like cell or home?
	
	> If I can obtain any of that information from the customer, I would then follow those trails.  I would not trust the report that refreshing the page doesn't fix the issue - I would try that out!  Hopefully our test environment is very similar to production and populated with data very similar to production.  I would do a series of experiments to try to test one thing at a time.  Probably the easiest thing to do first is to actually go try to change a test user's phone number in all the places it can be changed and see what happens in the UI and in the logs.  I would be looking for any validation issues on the form itself, depending on what number I entered.  I would try many different phone numbers - valid and invalid, with a variety of symbols - to see what happens.  I would be looking for errors in the logs that might indicate a failed POST or GET request, a permissions error, a database error or other persistence-type error, a timeout error, etc.  I would open a web debugging proxy like Fiddler to see the http traffic.  I would be looking for indications that there were issues in one or more of these areas: user behavior, browser/client-side, server-side, persistence.  I would look at the persistent storage to see what's being saved for the user.
