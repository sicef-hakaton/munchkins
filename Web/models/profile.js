/**
 * Created by MilenCHE on 11/22/2014.
 */
var mongoose = require('mongoose');
var crypto = require('crypto');

/* Schema for profile data. */

var profileSchema = mongoose.Schema({
    email: String,
    password: String,
    profileid: String,
    first_name: String,
    last_name: String,
    picture: String,
    tags_interested: [String],
    tags_teach: [String]
});

// methods ======================
// generating a hash
profileSchema.methods.generateHash = function(password) {
    return crypto.createHash('sha512').update(password).digest('hex');
};

// checking if password is valid
profileSchema.methods.validPassword = function(password) {
    return crypto.createHash('sha512').update(password).digest('hex') === this.password;
};

module.exports = mongoose.model("Profile", profileSchema);