/**
 * Created by MilenCHE on 11/23/2014.
 */
var mongoose = require('mongoose');

/* Schema for profile data. */

var commentSchema = mongoose.Schema({
    text: String,
    theme: String,
    profile: String
});

module.exports = mongoose.model("Comment", commentSchema);