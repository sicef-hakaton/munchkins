/**
 * Created by MilenCHE on 11/22/2014.
 */
var LocalStrategy   = require('passport-local').Strategy;

// load up the profile model
var Profile = require('../models/profile');
var uuid = require('node-uuid');
var biguint = require('biguint-format');
var FlakeIdGen = require('flake-idgen')

var generator = new FlakeIdGen;

module.exports = function(passport) {

    // =========================================================================
    // passport session setup ==================================================
    // =========================================================================
    // required for persistent login sessions
    // passport needs ability to serialize and unserialize profiles out of session

    // used to serialize the profile for the session
    passport.serializeUser(function(profile, done) {
        done(null, profile.id);
    });

    // used to deserialize the profile
    passport.deserializeUser(function(id, done) {
        Profile.findById(id, function(err, profile) {
            done(err, profile);
        });
    });

    // =========================================================================
    // LOCAL SIGNUP ============================================================
    // =========================================================================
    // we are using named strategies since we have one for login and one for signup
    // by default, if there was no name, it would just be called 'local'

    passport.use('local-signup', new LocalStrategy({
            // by default, local strategy uses profilename and password, we will override with email
            profilenameField : 'email',
            passwordField : 'password',
            passReqToCallback : true // allows us to pass back the entire request to the callback
        },
        function(req, email, password, done) {

            Profile.findOne({'email': email}, function (err, profile) {
                // if there are any errors, return the error
                if (err)
                    return done(err);

                // check to see if theres already a profile with that email
                if (profile) {
                    return done(null, false, req.flash('signupMessage', 'emailTaken'));
                } else {
                    if (password.length < 8)
                        return done(null, false, req.flash('signupMessage', 'shortPassword'));
                    // if there is no profile with that email
                    // create the profile
                    var newProfile = new Profile();

                    //profileid
                    var profileid = biguint(generator.next(), 'dec');
                    console.log(profileid);

                    newProfile.email = email;
                    newProfile.password = newProfile.generateHash(password); // use the generateHash function in our profile model
                    newProfile.profileid = profileid;

                    // save the profile
                    newProfile.save(function (err) {
                        if (err)
                            throw err;

                        emailVerification.generateToken(newProfile.email);
                        console.log(newProfile);
                        return done(null, newProfile);
                    });
                }

            });
        }));
    
    // =========================================================================
    // LOCAL LOGIN==============================================================
    // =========================================================================
    // we are using named strategies since we have one for login and one for signup
    // by default, if there was no name, it would just be called 'local'

    passport.use('local-login', new LocalStrategy({
            // by default, local strategy uses profilename and password, we will override with email
            profilenameField : 'email',
            passwordField : 'password',
            passReqToCallback : true // allows us to pass back the entire request to the callback
        },
        function(req, email, password, done) { // callback with email and password from our form
            // find a profile whose email is the same as the forms email
            // we are checking to see if the profile trying to login already exists
            Profile.findOne({ 'email': email }, function (err, profile) {
                // if there are any errors, return the error before anything else
                if (err)
                    return done(err);

                // if no profile is found, return the message
                if (!profile)
                    return done(null, false, req.flash('loginMessage', 'failedLogin')); // req.flash is the way to set flashdata using connect-flash

                // if the profile is found but the password is wrong
                if (!profile.validPassword(password))
                    return done(null, false, req.flash('loginMessage', 'failedLogin')); // create the loginMessage and save it to session as flashdata

                // if the profile is not verified
                if (!profile.verified)
                    return done(null, false, req.flash('loginMessage', 'failedLogin')); // create the loginMessage and save it to session as flashdata


                // all is well, return successful profile
                return done(null, profile);
            });
        }));

};