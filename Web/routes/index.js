var express = require('express');
var passport = require('passport');
var router = express.Router();

/* GET home page. */

router.get('/', function(req, res) {
//  if(req.isAuthenticated())
    res.render('index', { title: 'Express' });

//  res.redirect("/login");
});

//router.get('/', function(req, res) {
//  if(req.isAuthenticated())
//    res.render('index', { title: 'Express' });
//
//  res.redirect("/login");
//});


/* GET profile data. */
router.post('/signup', passport.authenticate('local-signup', {
    successRedirect : '/', // redirect to the secure profile section
    failureRedirect : '/signup', // redirect back to the signup page if there is an error
    failureFlash : true // allow flash messages
}));

module.exports = router;
